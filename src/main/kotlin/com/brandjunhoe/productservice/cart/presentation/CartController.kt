package com.brandjunhoe.productservice.cart.presentation

import com.brandjunhoe.productservice.cart.application.dto.CartDTO
import com.brandjunhoe.productservice.common.response.CommonResponse
import com.brandjunhoe.productservice.cart.application.CartService
import com.brandjunhoe.productservice.cart.presentation.dto.ReqCartSaveDTO
import com.brandjunhoe.productservice.cart.presentation.dto.ReqCartUpdateDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/v1/cart")
class CartController(private val cartService: CartService) {


    @PostMapping
    fun save(
        @RequestBody @Valid req: ReqCartSaveDTO
    ): CommonResponse<Unit> {
        cartService.save(req)
        return CommonResponse(HttpStatus.CREATED)
    }

    @GetMapping
    fun findAllByUsr(
        @RequestParam("usrId") @Valid @NotBlank usrId: UUID
    ): CommonResponse<List<CartDTO>> = CommonResponse(cartService.findAllByUsr(usrId))


    @PatchMapping("/{id}")
    fun updateById(
        @PathVariable("id") @Valid @NotBlank id: UUID,
        @RequestBody req: ReqCartUpdateDTO
    ): CommonResponse<Unit> {
        cartService.updateById(id, req)
        return CommonResponse()
    }

    @DeleteMapping("/{id}")
    fun deleteCart(
        @PathVariable("id") @Valid @NotBlank id: UUID
    ): CommonResponse<Unit> {
        cartService.deleteById(id)
        return CommonResponse()
    }

}