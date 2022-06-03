package com.brandjunhoe.productservice.product.domain.converter

import com.brandjunhoe.productservice.product.domain.ItemValue
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import javax.persistence.AttributeConverter
import javax.persistence.Converter


/**
 * Create by DJH on 2022/04/27.
 */
@Converter
class ItemConverter : AttributeConverter<List<Map<Any, String>>, String> {

    private val mapper: ObjectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)

    override fun convertToDatabaseColumn(attribute: List<Map<Any, String>>): String {
        return try {
            mapper.writeValueAsString(attribute)
        } catch (e: JsonProcessingException) {
            throw IllegalArgumentException()
        }
    }

    override fun convertToEntityAttribute(dbData: String): List<Map<Any, String>> {
        return try {
            return mapper.readValue(dbData, object : TypeReference<List<Map<Any, String>>>() {})
        } catch (e: IOException) {
            throw java.lang.IllegalArgumentException()
        }
    }

}
