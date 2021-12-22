package br.com.samuel.productapi.rabbitmq;

import br.com.samuel.productapi.dtos.product.ProductStockDTO;
import br.com.samuel.productapi.services.Product.ProductInterfaces;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProductStockListener {

    @Autowired
    private ProductInterfaces productService;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void receiveProductStockMessage(ProductStockDTO product) throws JsonProcessingException {
        log.info("Recebendo mensagem: {}", new ObjectMapper().writeValueAsString(product));
        productService.updateProductStock(product);
    }

}
