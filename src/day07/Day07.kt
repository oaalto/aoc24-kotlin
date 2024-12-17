package day07

import println
import readLines
import kotlin.math.pow

fun main() {
    val lines = readLines("day07/day07")
    part1(lines).println()
}

fun part1(lines: List<String>): Long {
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
                    Add -> acc + operand
                    Mul -> acc * operand
                }
            }
            r == result
        }
    }
}

sealed class Operator
data object Add : Operator()
data object Mul : Operator()

fun getOperatorPermutations(n: Int): List<List<Operator>> {
    return 0.rangeUntil(2.0.pow(n).toInt()).map { pow ->
        0.rangeUntil(n).mapIndexed { index, _ ->
            when (pow.shr(index) and 0x1) {
                0 -> Add
                else -> Mul
            }
        }
    }
}