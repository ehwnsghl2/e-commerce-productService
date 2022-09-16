package com.brandjunhoe.productservice.wish.application

import com.brandjunhoe.productservice.client.UserImplClient
import com.brandjunhoe.productservice.client.dto.UserDTO
import com.brandjunhoe.productservice.common.ext.convertStrToLocalDateTime
import com.brandjunhoe.productservice.product.domain.ProductRepository
import com.brandjunhoe.productservice.wish.application.dto.WishDTO
import com.brandjunhoe.productservice.wish.application.exception.WishAlreadyException
import com.brandjunhoe.productservice.wish.application.exception.WishNotFoundException
import com.brandjunhoe.productservice.wish.application.exception.WishProductNotMatchingException
import com.brandjunhoe.productservice.wish.domain.Wish
import com.brandjunhoe.productservice.wish.domain.WishRepository
import com.brandjunhoe.productservice.wish.presentation.dto.ReqWishSaveDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class WishService(
    private val userClient: UserImplClient,
    private val wishRepository: WishRepository,
    private val productRepository: ProductRepository
) {

    fun save(req: ReqWishSaveDTO) {
        findByUsrId(req.usrId)

        wishRepository.findByUsrIdAndProductCode(req.usrId, req.productCode)
            ?: throw WishAlreadyException()

        wishRepository.save(req.toEntity())
    }

    @Transactional(readOnly = true)
    fun findAllByUsr(usrId: UUID): List<WishDTO> {

        val user = findByUsrId(usrId)
        val wishs = wishRepository.findByUsrId(user.id)
        val products = productRepository.findByProductCodeIn(wishs.map { it.productCode })

        return wishs.map { wish ->
            products.find { product -> wish.productCode == product.productCode }?.let {
                WishDTO(wish.id!!, it.mainImage.path.toString(), it.name, convertStrToLocalDateTime(wish.regdate))
            } ?: throw WishProductNotMatchingException()

        }
    }

    fun deleteById(id: UUID) {
        wishRepository.deleteById(id)
    }

    private fun findByUsrId(usrId: UUID): UserDTO =
        userClient.findByUser(usrId)

    private fun findById(id: UUID): Wish =
        wishRepository.findById(id) ?: throw WishNotFoundException()


}