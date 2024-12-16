package day01

import readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun testPart1() {
        val lines = readLines("day01/day01_test")
        val result = part1(lines)
        assertEquals(11, result)
    }

    @Test
    fun testPart2() {
        val lines = readLines("day01/day01_test")
        val result = part2(lines)
        assertEquals(31, result)
    }
}