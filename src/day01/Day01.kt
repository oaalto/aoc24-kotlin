package day01

import println
import readLines
import kotlin.math.abs

fun main() {
    val input = readLines("day01/day01")
    part1(input).println()
    part2(input).println()
}

fun part1(input: List<String>): Long {
    val (left, right) = convertToLists(input)

    val result = left.sorted().zip(right.sorted()).sumOf { (left, right) ->
        abs(left - right)
    }

    return result
}

fun part2(input: List<String>): Long {
    val (left, right) = convertToLists(input)

    val similarityMap = right.groupingBy { it }.eachCount()

    val result = left.sumOf { it * similarityMap.getOrDefault(it, 0) }

    return result
}

private fun convertToLists(input: List<String>): Pair<List<Long>, List<Long>> = input.map {
    val left = it.substringBefore(" ").toLong()
    val right = it.substringAfterLast(" ").toLong()
    left to right
}.unzip()
