package com.brandjunhoe.productservice.client

import com.brandjunhoe.productservice.client.dto.OrderProductDTO
import com.brandjunhoe.productservice.common.response.CommonResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * Create by DJH on 2022/04/05.
 */
@FeignClient(name = "order-service")
interface OrderClient {

    @GetMapping("/order/product/{orderProductCode}")
    fun findByOrderProduct(@PathVariable orderProductCode: String): CommonResponse<OrderProductDTO>


}