package com.brandjunhoe.productservice

import com.brandjunhoe.productservice.common.calculator.rate
import org.junit.jupiter.api.Test
import java.math.BigDecimal

/**
 * Create by DJH on 2022/06/01.
 */
class CalculatorTest {

    @Test
    fun rateTest(){

        val amount = BigDecimal.valueOf(10000)
        val rate = 3
        val rateAmount = rate(amount, rate)

        println(rateAmount)


    }
}