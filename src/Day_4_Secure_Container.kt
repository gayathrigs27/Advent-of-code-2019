private val filename = "src/input/Day_4_Secure_ContainerInput"

fun computeCombinations(input: List<Int>): List<String> {
    val combinationsList = (input[0]..input[1]).map { value -> value.toString() }
        .filter { valueString -> valueString.toSet().size < 6 && valueString.toList().sorted() == valueString.toList() }
    return combinationsList
}

fun part2(computeCombinations: List<String>): Int {
    return computeCombinations.map { value->
        value.toList().groupBy { it }.filter { it.value.size ==2 }.isNotEmpty()
    }.filter { it }.size
}

fun main() {
    val input = helpers.convertInputToIntList(filename, '-')
    val computeCombinations = computeCombinations(input)
    println("Number of combinations:${computeCombinations.size}")
    println("Number of combinations for part 2:${(part2(computeCombinations))}")
}
