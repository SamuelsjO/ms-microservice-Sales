package br.com.samuel.productapi.sales.client;

import br.com.samuel.productapi.dtos.product.ProductSalesResponse;
import br.com.samuel.productapi.sales.dto.SalesProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "salesClient",
        contextId = "salesClient",
        url = "${app-config.services.sales}"
)
public interface SalesClient {

    @GetMapping("products/{productId}")
    Optional<SalesProductResponse> findSalesProductId(@PathVariable Integer productId);

}
