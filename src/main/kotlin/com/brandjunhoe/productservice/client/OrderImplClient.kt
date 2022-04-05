package com.brandjunhoe.productservice.client

import com.brandjunhoe.productservice.client.dto.OrderProductDTO
import com.brandjunhoe.productservice.common.exception.BadRequestException
import feign.FeignException
import org.springframework.stereotype.Component

/**
 * Create by DJH on 2022/04/05.
 */
@Component
class OrderImplClient(val orderClient: OrderClient) {

    fun findByOrderProduct(orderProductCode: String): OrderProductDTO =
        orderClient.findByOrderProduct(orderProductCode).data!!


}
