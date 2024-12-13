package day03

import println
import read

fun main() {
    val contents = read("day03/day03")
    part1(contents).println()
    part2(contents).println()
}

object Day03 {
    val mulReg = Regex("mul\\((\\d+),(\\d+)\\)")
    val mulAndDoOrDontReg = Regex("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)")
}

fun part1(contents: String): Long {
    return Day03.mulReg.findAll(contents)
        .map { result -> result.groupValues }
        .map { (_, v1, v2) -> v1.toLong() * v2.toLong() }
        .sum()
}

fun part2(contents: String): Long {
    return Day03.mulAndDoOrDontReg.findAll(contents)
        .map { result -> result.groupValues }
        .fold(Result(0, true)) { result, (match, v1, v2) ->
            when (match.substring(0, 3)) {
                "mul" -> {
                    if (result.enabled) {
                        result + v1.toLong() * v2.toLong()
                    } else {
                        result
                    }
                }

                "do(" -> Result(result.value, true)
                "don" -> Result(result.value, false)
                else  -> throw IllegalArgumentException("TODO")
            }
        }.value
}

data class Result(val value: Long, val enabled: Boolean) {
    operator fun plus(other: Long): Result {
        return Result(value + other, enabled)
    }
}
