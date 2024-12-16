package day04

import println
import readLines

fun main() {
    val lines = readLines("day04/day04")
    part1(lines).println()
    part2(lines).println()
}

fun part1(lines: List<String>): Int {
    return XmasMap(lines[0].length, lines.size, lines).check()
}

fun part2(lines: List<String>): Int {
    return CrossedXmasMap(lines[0].length, lines.size, lines).check()
}