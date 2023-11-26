package io.github.eendroroy.exposed.demo.config

import java.time.format.DateTimeFormatter

enum class DateTimePattern(pattern: String) {
    DATE_TIME_SHORT_12(Patterns.DATE_TIME_SHORT_12_VALUE),
    DATE_TIME_SHORT_24(Patterns.DATE_TIME_SHORT_24_VALUE);

    val format: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
}

object Patterns {
    const val DATE_TIME_SHORT_12_VALUE = "yyyy-MM-dd hh:mm a"
    const val DATE_TIME_SHORT_24_VALUE = "yyyy-MM-dd HH:mm"
}
