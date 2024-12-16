package day06

import println
import readLines

fun main() {
    val lines = readLines("day06/day06")
    part1(lines).println()
}

fun part1(lines: List<String>): Int {
    val map = Map(lines)
    return map.simulate()
}