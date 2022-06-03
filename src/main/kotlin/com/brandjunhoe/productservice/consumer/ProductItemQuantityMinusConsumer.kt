package com.brandjunhoe.productservice.consumer

import com.brandjunhoe.productservice.consumer.dto.ProductItemQuantityMinusUpdateDTO
import com.brandjunhoe.productservice.product.application.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.listener.AcknowledgingMessageListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

/**
 * Create by DJH on 2022/04/18.
 */
@Component
class ProductItemQuantityMinusConsumer(
    private val productService: ProductService,
    private val objectMapper: ObjectMapper
) : AcknowledgingMessageListener<String, String> {


    @KafkaListener(
        topics = ["\${spring.kafka.consumer.topic}"],
        groupId = "\${spring.kafka.consumer.group-id}"
    )
    override fun onMessage(data: ConsumerRecord<String, String>, acknowledgment: Acknowledgment?) {
        val data = objectMapper.readValue(data.value(), ProductItemQuantityMinusUpdateDTO::class.java)
        productService.updateProductItemStockMinus(data)
        acknowledgment?.acknowledge()
    }


}


// {"usrId" : "6efb1bf8-d3d3-472a-876e-223a2798eb56", "orderCode" : "orderCode", "productCode" : "productCode",  "type" : "PRODUCT", "amount" : 1000}
