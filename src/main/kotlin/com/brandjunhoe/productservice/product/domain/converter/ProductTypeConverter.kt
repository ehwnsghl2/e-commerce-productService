package com.brandjunhoe.productservice.product.domain.converter

import com.brandjunhoe.productservice.product.domain.enums.ProductTypeEnum
import javax.persistence.AttributeConverter
import javax.persistence.Converter

/**
 * Create by DJH on 2022/04/27.
 */
@Converter
class ProductTypeConverter : AttributeConverter<String, ProductTypeEnum> {

    override fun convertToDatabaseColumn(attribute: String?): ProductTypeEnum {
        return attribute?.let { ProductTypeEnum.valueOf(it) } ?: ProductTypeEnum.PRODUCT
    }

    override fun convertToEntityAttribute(dbData: ProductTypeEnum): String {
       return dbData.desc
    }
}
