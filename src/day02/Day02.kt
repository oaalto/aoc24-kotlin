package day02

import println
import readInput

fun main() {
    val lines = readInput("day02/day02")
    part1(lines).println()
    part2(lines).println()
}

fun part1(input: List<String>): Int {
    val result = input.map(::parseInput).map(::Report).filter(Report::isValid)
    return result.size
}

fun part2(input: List<String>): Int {
    val result = input
        .map(::parseInput)
        .map(::Report)
        .map(ProblemDampener::tryToFixReport)
        .filter(Report::isValid)

    return result.size
}

fun parseInput(input: String): List<Long> {
    return input.split(" ").map(String::toLong)
}