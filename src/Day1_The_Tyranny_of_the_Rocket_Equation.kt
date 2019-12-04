import java.io.File
import kotlin.math.max

private val filename = "src/input/TheTyrannyoftheRocketEquationInput"

fun calculateFuelForModule(mass: Int): Int {
    return max((mass / 3) - 2,0)
}

fun fuelRequiredForModule(filename: String): Int {
    val readLines: List<String> = File(filename).readLines()
    return readLines.sumBy { calculateFuelForModule(it.toInt()) }
}

fun calculateTotalFuel(mass: Int): Int {
    var fuel = calculateFuelForModule(mass)
    var totalFuel = fuel
    while (fuel != 0) {
        fuel = calculateFuelForModule(fuel)
        totalFuel += fuel
    }
    return totalFuel
}

fun totalFuelRequired(filename: String): Int {
    val readLines: List<String> = File(filename).readLines()
    return readLines.sumBy { calculateTotalFuel(it.toInt()) }
}

fun main() {
    println("Total fuel=${fuelRequiredForModule(filename)}")
    println("Total fuel including fuel mass=${totalFuelRequired(filename)}")
}
