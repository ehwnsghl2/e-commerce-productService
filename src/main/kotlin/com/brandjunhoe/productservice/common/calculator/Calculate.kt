package com.brandjunhoe.productservice.common.calculator

import java.math.BigDecimal


fun rate(target: BigDecimal, rate: Int): Int =
    ((rate / 100.0) * target.toInt()).toInt()

