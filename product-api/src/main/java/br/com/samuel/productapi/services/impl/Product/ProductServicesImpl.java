package br.com.samuel.productapi.services.impl.Product;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.dtos.product.*;
import br.com.samuel.productapi.exception.ValidationException;
import br.com.samuel.productapi.models.Product;
import br.com.samuel.productapi.repository.ProductRepository;
import br.com.samuel.productapi.sales.client.SalesClient;
import br.com.samuel.productapi.sales.dto.SalesConfirmationDTO;
import br.com.samuel.productapi.sales.enums.SalesStatus;
import br.com.samuel.productapi.sales.rabbitmq.SalesConfirmationSender;
import br.com.samuel.productapi.services.Product.ProductInterfaces;
import br.com.samuel.productapi.services.Supplier.SupplierInterfaces;
import br.com.samuel.productapi.services.category.CategoryInterfaces;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
public class ProductServicesImpl implements ProductInterfaces {

    private static final Integer ZERO = 0;

    @Autowired
    private ProductRepository repository;
    @Autowired
    private SupplierInterfaces supplierInterfaces;
    @Autowired
    private CategoryInterfaces categoryInterfaces;
    @Autowired
    private SalesConfirmationSender salesConfirmationSender;
    @Autowired
    private SalesClient salesClient;

    @Override
    public Product findById(Integer id) {
        validateInformedId(id);
        return repository.findById(id).orElseThrow(() -> new ValidationException("There's no product for the given ID"));
    }

    @Override
    public ProductResponse findByIdResponse(Integer id) {
        validateInformedId(id);
        return ProductResponse.of(findById(id));
    }

    @Override
    public ProductResponse save(ProductRequest request) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierInformed(request);

        var category = categoryInterfaces.findById(request.getCategoryId());
        var supplier = supplierInterfaces.findById(request.getSupplierId());

        var product = repository.save(Product.of(request, supplier, category));
        return ProductResponse.of(product);
    }

    @Override
    public ProductResponse update(ProductRequest request, Integer id) {
        validateProductDataInformed(request);
        validateCategoryAndSupplierInformed(request);
        validateInformedId(id);

        var category = categoryInterfaces.findById(request.getCategoryId());
        var supplier = supplierInterfaces.findById(request.getSupplierId());

        var product = Product.of(request, supplier, category);
        product.setId(id);
        repository.save(product);

        return ProductResponse.of(product);
    }

    @Override
    public List<ProductResponse> findByNameIgnoreCaseContaining(String name) {
        if (isEmpty(name)) {
            throw new ValidationException("The supplier name must be informed");
        }
        return repository.findByNameIgnoreCaseContaining(name).stream().map(ProductResponse::of).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findByCategoryId(Integer categoryId) {
        if (isEmpty(categoryId)) {
            throw new ValidationException("The product category ID must be informed");
        }
        return repository.findByCategoryId(categoryId).stream().map(ProductResponse::of).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findBySupplierId(Integer supplierId) {
        if (isEmpty(supplierId)) {
            throw new ValidationException("The product supplier ID must be informed");
        }
        return repository.findBySupplierId(supplierId).stream().map(ProductResponse::of).collect(Collectors.toList());
    }


    @Override
    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(ProductResponse::of).collect(Collectors.toList());
    }

    @Override
    public Boolean existCategoryId(Integer id) {
        return repository.existsByCategoryId(id);
    }

    @Override
    public Boolean existSupplierId(Integer id) {
        return repository.existsBySupplierId(id);
    }

    @Override
    public SuccessResponse delete(Integer id) {
        validateInformedId(id);
        repository.deleteById(id);
        return SuccessResponse.create("The product wa delete");
    }

    private void validateProductDataInformed(ProductRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The product description was not informed. ");
        }

        if (isEmpty(request.getQuantityAvailable())) {
            throw new ValidationException("The product quantityAvailable was not informed. ");
        }

        if (isEmpty(request.getQuantityAvailable() <= ZERO)) {
            throw new ValidationException("The quantity should not be less or equal a zero. ");
        }
    }

    private void validateCategoryAndSupplierInformed(ProductRequest request) {
        if (isEmpty(request.getSupplierId())) {
            throw new ValidationException("The product supplier ID was not informed. ");
        }

        if (isEmpty(request.getCategoryId())) {
            throw new ValidationException("The product category ID was not informed. ");
        }
    }

    private void validateInformedId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("The product ID must be informed.");
        }
    }

    private void validateStockUpdateData(ProductStockDTO productDto) {
        if (isEmpty(productDto) || isEmpty(productDto.getSalesId())) {
            throw new ValidationException("The product data and the sales ID must be informed");
        }
        if (isEmpty(productDto.getProducts())) {
            throw new ValidationException("The sales products must be informed");
        }
        productDto.getProducts().forEach(salesProduct -> {
            if (isEmpty(salesProduct.getQuantity()) || isEmpty(salesProduct.getProductId())) {
                throw new ValidationException("The productID and the quantity must be informed.");
            }
        });
    }

    @Override
    public void updateProductStock(ProductStockDTO productDto) {
        try {
            validateStockUpdateData(productDto);
            updateStock(productDto);
        } catch (Exception ex) {
            log.error("Error while trying to update stock for message with error: {}", ex.getMessage(), ex);
            salesConfirmationSender.sendSalesConfirmationMessage(new SalesConfirmationDTO(productDto.getSalesId(), SalesStatus.REJECTED));
        }

    }

    @Override
    public ProductSalesResponse findProductSales(Integer id) {
        var product = findById(id);
        try {
            var sales = salesClient.findSalesProductId(product.getId())
                    .orElseThrow(() -> new ValidationException("The sales was not found by this product."));
            return ProductSalesResponse.of(product, sales.getSalesIds());
        } catch (Exception ex) {
            throw new ValidationException("There was an error trying  to get the product's sales");
        }
    }

    @Override
    public SuccessResponse checkProductsStock(ProductCheckStockRequest request) {
        if (isEmpty(request) || isEmpty(request.getProducts())) {
            throw new ValidationException("The request data and products must be informed.");
        }
        request
                .getProducts()
                .forEach(this::validateStock);
        return SuccessResponse.create("The stock is ok!.");
    }

    private void validateStock(ProductQuantityDTO productQuantity) {
        if (isEmpty(productQuantity.getProductId()) || isEmpty(productQuantity.getQuantity())) {
            throw new ValidationException("Product ID and quantity must be informed.");
        }
        var product = findById(productQuantity.getProductId());
        if (productQuantity.getQuantity() > product.getQuantityAvailable()) {
            throw new ValidationException(String.format("The product %s is out of start.", product.getId()));

        }
    }

    @Transactional
    private void updateStock(ProductStockDTO productDto) {
        var productForUpdate = new ArrayList<Product>();
        productDto.getProducts().forEach(salesProduct -> {
            var existingProduct = findById(salesProduct.getProductId());
            validateQuantityInStock(salesProduct, existingProduct);
            existingProduct.updateStock(salesProduct.getQuantity());
            productForUpdate.add(existingProduct);
        });
        if (!isEmpty(productForUpdate)) {
            repository.saveAll(productForUpdate);
            var approvedMessage = new SalesConfirmationDTO(productDto.getSalesId(), SalesStatus.APROVED);
            salesConfirmationSender.sendSalesConfirmationMessage(approvedMessage);
        }
    }

    private void validateQuantityInStock(ProductQuantityDTO salesProduct, Product existingProduct) {
        if (salesProduct.getQuantity() > existingProduct.getQuantityAvailable()) {
            throw new ValidationException(String.format("The product %s out of stock", existingProduct.getId()));
        }
    }


}
