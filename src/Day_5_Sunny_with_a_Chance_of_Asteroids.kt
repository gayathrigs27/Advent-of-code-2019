import java.util.Arrays.asList

private val filename = "src/input/Day_5_Sunny_with_a_Chance_of_AsteroidsInput"

val opcodeList = helpers.convertInputToIntList(filename, ',')

fun diagnosticProgram(opcodesList: List<Int>, input: Int): Int {
    val opcodes = opcodesList.toMutableList()
    var i = 0
    var result = 0
    while (i < opcodes.size) {
        val operationParameter = getValues(opcodes, i)
        when (operationParameter.first()) {
            1 -> {
                opcodes[opcodes[i + 3]] = opcodes[operationParameter[1]] + opcodes[operationParameter[2]]
                i += 4

            }
            2 -> {
                opcodes[opcodes[i + 3]] = opcodes[operationParameter[1]] * opcodes[operationParameter[2]]
                i += 4

            }
            3 -> {
                opcodes[operationParameter[1]] = input
                i += 2

            }
            4 -> {
                result = opcodes[operationParameter[1]]
                i += 2

            }
            5 -> {
                if (opcodes[operationParameter[1]] != 0)
                    i = opcodes[operationParameter[2]]
                else i+=3
            }
            6 -> {
                if (opcodes[operationParameter[1]] == 0)
                    i = opcodes[operationParameter[2]]
                else i+=3
            }
            7 -> {
                if (opcodes[operationParameter[1]] < opcodes[operationParameter[2]]) {
                    opcodes[opcodes[i + 3]] = 1
                }
                else opcodes[opcodes[i + 3]] = 0
                i+=4
            }
            8 -> {
                 if (opcodes[operationParameter[1]] == opcodes[operationParameter[2]]) {
                    opcodes[opcodes[i + 3]] = 1
                }
                else opcodes[opcodes[i + 3]] = 0
                i+=4
            }
            99 -> return result
        }
    }
    return result
}

private fun getValues(opcodes: MutableList<Int>, i: Int): List<Int> {
    val instruction = opcodes[i] % 100
    val parameter1 = opcodes[i] / 100 % 10
    val parameter2 = opcodes[i] / 1000
    if(instruction==99){
        return asList(instruction)
    }
    return asList(instruction, parameterMode(opcodes, 1, i, parameter1), parameterMode(opcodes, 2, i, parameter2))
}

private fun parameterMode(opcodes: MutableList<Int>, position: Int, i: Int, mode: Int): Int {
    when (mode) {
        0 -> return opcodes[i + position]
        1 -> return i + position
    }
    return Int.MAX_VALUE
}

fun main() {
    println("Result:${diagnosticProgram(opcodeList,1)}")
    println("Result:${diagnosticProgram(opcodeList,5)}")
}

