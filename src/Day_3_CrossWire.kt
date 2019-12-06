import java.io.File
import java.lang.Math.abs

private val filename = "src/input/Day_3_CrossWireInput"

fun convertInputToTravelClass(filename: String): List<List<Instruction>> {
    return File(filename).readLines().map {
        it.split(',').map { chars ->
            Instruction(
                Direction.valueOf(chars.take(1)),
                chars.drop(1).toInt()
            )
        }
    }
}

enum class Direction {
    U, D, L, R

}

data class Instruction(val direction: Direction, val distance: Int)

data class Point(val x: Int, val y: Int)

operator fun Point.plus(instruction: Instruction): List<Point> {
    return when (instruction.direction) {
        Direction.D -> (1..instruction.distance).map { copy(y = y - it) }
        Direction.U -> (1..instruction.distance).map { copy(y = y + it) }
        Direction.R -> (1..instruction.distance).map { copy(x = x + it) }
        Direction.L -> (1..instruction.distance).map { copy(x = x - it) }
    }
}

fun Point.distance(): Int = abs(x) + abs(y)

fun Point.wireDistance(wires: List<List<Point>>): Int {
    val index = wires.map { list -> list.indexOf(Point(x, y)) }
    return index[0] + index[1] + 2
}

private fun getIntersectionPoints(wirePaths: List<List<Point>>): Set<Point> {
    return wirePaths[0].intersect(wirePaths[1])
}

private fun getWirePath(travel: List<List<Instruction>>): List<List<Point>> {
    return travel
        .map {
            val fold = it.fold(mutableListOf(Point(0, 0))) { list, next ->
                list.apply { addAll(last() + next) }
            }
            fold.drop(1)
        }
}

fun computeManhattanDistance(wirePaths: List<List<Point>>): Int {
    return getIntersectionPoints(wirePaths).sortedBy { it.distance() }
        .first()
        .distance()
}

fun computeFinalManhattanDistance(wiresPath: List<List<Point>>): Int {
    return getIntersectionPoints(wiresPath).map { it.wireDistance(wiresPath) }.sorted().first()
}


fun main() {
    val instructionList = convertInputToTravelClass(filename)
    val wiresPaths = getWirePath(instructionList)
    println("Manhattan distance:${computeManhattanDistance(wiresPaths)}")
    println("Manhattan distance 2:${computeFinalManhattanDistance(wiresPaths)}")
}
