package io.github.eendroroy.exposed.demo.util

import java.security.MessageDigest

fun String.md5(): String = MessageDigest.getInstance("MD5").let { md5 ->
    md5.update(this.toByteArray())
    md5.digest().joinToString("") {
        java.lang.Byte.toUnsignedInt(it).toString(radix = 16).padStart(2, '0')
    }
}
