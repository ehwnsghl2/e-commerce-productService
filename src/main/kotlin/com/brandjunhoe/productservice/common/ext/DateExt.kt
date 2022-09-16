package com.brandjunhoe.productservice.common.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * Create by DJH on 2022/04/06.
 */
const val DEFAULT_DATE_PATTERN = "yyyy-MM-dd"
const val DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"

fun convertStrToLocalDateTime(date: Date?, format: String = DEFAULT_DATE_PATTERN): String =
    SimpleDateFormat(format, Locale.getDefault()).format(date)

