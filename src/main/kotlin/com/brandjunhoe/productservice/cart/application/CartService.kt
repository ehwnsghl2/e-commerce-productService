package com.brandjunhoe.productservice.cart.application

import com.brandjunhoe.productservice.cart.application.dto.CartDTO
import com.brandjunhoe.productservice.cart.application.exception.CartNotFoundException
import com.brandjunhoe.productservice.cart.application.exception.CartProductNotMatchingException
import com.brandjunhoe.productservice.cart.domain.Cart
import com.brandjunhoe.productservice.cart.domain.CartRepository
import com.brandjunhoe.productservice.cart.presentation.dto.ReqCartSaveDTO
import com.brandjunhoe.productservice.cart.presentation.dto.ReqCartUpdateDTO
import com.brandjunhoe.productservice.client.UserImplClient
import com.brandjunhoe.productservice.client.dto.UserDTO
import com.brandjunhoe.productservice.product.domain.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CartService(
    private val userClient: UserImplClient,
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
) {


    @Transactional
    fun save(req: ReqCartSaveDTO) {

        findByUser(req.usrId)

        val cart = cartRepository.findByUsrIdAndProductCodeAndItemCode(req.usrId, req.productCode, req.itemCode)

        cart?.run { addQuantity(req.quantity) }
            ?: cartRepository.save(req.toEntity())

    }

    @Transactional(readOnly = true)
    fun findAllByUsr(usrId: UUID): List<CartDTO> {

        val user = findByUser(usrId)
        val carts = cartRepository.findByUsrId(user.id)
        val products = productRepository.findByProductCodeIn(carts.map { it.productCode })

        return carts.map { cart ->
            products.find { product -> cart.productCode == product.productCode }?.let {
                CartDTO(
                    cart.id!!,
                    it.productCode.productCode,
                    it.mainImage.path.toString(),
                    it.name,
                    it.retailPrice,
                    it.sellingPrice,
                    it.sellingState,
                    it.discountRate,
                    it.discountPrice,
                    it.soldOutState
                )
            } ?: throw CartProductNotMatchingException()
        }

    }

    @Transactional
    fun updateById(id: UUID, req: ReqCartUpdateDTO) {
        val cart = findById(id)
        cart.changeQuantity(req.quantity)
    }

    @Transactional
    fun deleteById(id: UUID) {
        val cart = findById(id)
        cart.delete()
    }

    private fun findByUser(usrId: UUID): UserDTO =
        userClient.findByUser(usrId)

    private fun findById(id: UUID): Cart =
        cartRepository.findById(id) ?: throw CartNotFoundException()

}