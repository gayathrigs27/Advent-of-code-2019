import java.io.File

private val filename = "src/input/Day_6_Universal_Orbit_MapInput"

fun convertInputToMap(filename: String): Map<String, String> {
    return File(filename).readLines().map {
        it.split(')')
    }.map {
        it[1] to it[0]
    }.toMap()
}

fun numberOfOrbits(inputs: Map<String, String>): Int {
    return inputs.keys.map { val orbits = inputs.getOrbits(it)
        orbits.size }.sum()
}

private fun Map<String, String>.getOrbits(key: String, list: List<String> = emptyList()): List<String> {
    return if (containsKey(key)) {
        getOrbits(getValue(key), list + getValue(key))
    }
    else list
}

fun numberOfJumps(inputs: Map<String, String>): Int {
    var youOrbits:ArrayList<String> = inputs.getOrbits("YOU") as ArrayList<String>
    var sanOrbits:ArrayList<String> = inputs.getOrbits("SAN") as ArrayList<String>
     youOrbits.addAll(sanOrbits)
    return youOrbits.groupBy { it }.filter { map -> map.value.size==1}.size

}

fun main() {
    val inputs = convertInputToMap(filename)
    println("Result 1:${numberOfOrbits(inputs)}")
    println("Result 2:${numberOfJumps(inputs)}")
}
