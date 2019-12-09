
import java.io.File

private val filename = "src/input/Day_8_SpaceImageFormatInput"

fun main() {
    val inputs = File(filename).readText()
    println("Result 1:${imageFormat(inputs)}")
    println("${image(inputs)}")

}

fun image(inputs: String): String {
    val chunked = inputs.trim().asIterable().chunked(150)
    val layer = chunked.fold(List<Char>(150) { '2' }, { layer, next -> layer.merge(next) })
    val image = layer.map { if (it == '1') '*' else ' ' }.chunked(25).map { it.joinToString("") } .joinToString ( "\n" )
    return image
}

fun Pair<Char, Char>.merge() =
    if (first == '2') second else first

private fun List<Char>.merge(next: List<Char>): List<Char> {
    return this.zip(next).map { p -> p.merge() }
}


fun imageFormat(inputs: String): Int {
    val chunked = inputs.chunked(150)
    val minBy = chunked.map { image -> image.groupBy { it } }.minBy { it.getValue('0').size }
    return minBy!!.getValue('1').size * minBy.getValue('2').size
}
