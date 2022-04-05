package com.brandjunhoe.productservice.common.calculator

import java.math.BigDecimal


fun rate(target: BigDecimal, rate: Int): Int {
    return (rate / 100) * target.toInt()
}
