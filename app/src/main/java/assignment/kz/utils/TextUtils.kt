package assignment.kz.utils

object TextUtils {
    fun replaceId(value: String): String {

        var newResult = ""

        if (value.length > 6 && value.substring(0, 3) == "398") {
            if (value.substring(0, 3) == "398") {
                newResult = value.replaceFirst("398", "KZ")
            }

            when (newResult.substring(2, 4)) {
                "01" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "C")
                }
                "02" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "B")
                }
                "03" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "D")
                }
                "04" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "E")
                }
                "05" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "F")
                }
                "06" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "H")
                }
                "07" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "L")
                }
                "08" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "P")
                }
                "09" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "M")
                }
                "10" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "N")
                }
                "11" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "R")
                }
                "12" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "S")
                }
                "13" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "T")
                }
                "14" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "X")
                }
                "15" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "A")
                }
                "16" -> {
                    newResult = newResult.replaceFirst(newResult.substring(2, 4), "Z")
                }
            }
        }else{
            newResult = value
        }
        return newResult
    }
}
