package com.brandjunhoe.productservice.client

import com.brandjunhoe.productservice.client.dto.UserDTO
import com.brandjunhoe.productservice.common.response.CommonResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

/**
 * Create by DJH on 2022/04/05.
 */
@FeignClient(name = "user-service")
interface UserClient {

    @GetMapping("/user/{id}")
    fun findByUser(@PathVariable id: UUID): CommonResponse<UserDTO>


}