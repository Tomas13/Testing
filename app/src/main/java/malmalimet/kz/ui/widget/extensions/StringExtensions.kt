package kz.senim.common.extensions

fun String.equalsIgnoreCase(string: String): Boolean {
    return string.toLowerCase() == toLowerCase()
}