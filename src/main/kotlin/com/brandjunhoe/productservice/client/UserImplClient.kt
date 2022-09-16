package com.brandjunhoe.productservice.client

import com.brandjunhoe.productservice.client.dto.UserDTO
import org.springframework.stereotype.Component
import java.util.*

/**
 * Create by DJH on 2022/04/05.
 */
@Component
class UserImplClient(val userClient: UserClient) {

    fun findByUser(id: UUID): UserDTO =
        userClient.findByUser(id).data!!


}
