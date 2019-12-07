package helpers

import java.io.File

fun convertLineInputToStringList(filename: String) = File(filename).readLines()

fun convertInputToIntList(filename: String, c: Char): List<Int> {
    return File(filename).readText().split(c).map { it.toInt() }
}
