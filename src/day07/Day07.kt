package day07

import println
import readLines
import kotlin.math.pow

fun main() {
    val lines = readLines("day07/day07")
    runner(lines).println()
}

fun runner(lines: List<String>): Long {
    return lines.map { Equation(it) }
        .filter { it.isValid() }
        .sumOf { it.result }
}

class Equation(line: String) {
    private val tokens = line.split(":")
    val result = tokens.first().toLong()
    private val operands = tokens.last().trim().split(" ").map { it.toLong() }
    private val permutations = getOperatorPermutations(operands.size - 1)

    fun isValid(): Boolean {
        return permutations.any { permutation ->
            val r = operands.drop(1).foldIndexed(operands.first()) { index, acc, operand ->
                when (permutation[index]) {
                    Add -> Add.operate(acc, operand)
                    Mul -> Mul.operate(acc, operand)
                    Concat -> Concat.operate(acc, operand)
                }
            }
            r == result
        }
    }
}

sealed interface Operator {
    fun operate(v1: Long, v2: Long): Long
}

data object Add : Operator {
    override fun operate(v1: Long, v2: Long): Long {
        return v1 + v2
    }
}

data object Mul : Operator {
    override fun operate(v1: Long, v2: Long): Long {
        return v1 * v2
    }
}

data object Concat : Operator {
    override fun operate(v1: Long, v2: Long): Long {
        return (v1.toString() + v2.toString()).toLong()
    }
}

fun getOperatorPermutations(n: Int): List<List<Operator>> {
    return 0.rangeUntil(3.0.pow(n).toInt()).map { pow ->
        pow.toString(radix = 3).padStart(n, '0').map { c ->
            when (c) {
                '0' -> Add
                '1' -> Mul
                else -> Concat
            }
        }
    }

    /*return 0.rangeUntil(2.0.pow(n).toInt()).map { pow ->
        0.rangeUntil(n).mapIndexed { index, _ ->
            when (pow.shr(index) and 0x1) {
                0 -> Add
                else -> Mul
            }
        }
    }*/
}