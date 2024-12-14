package day04

import readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {
    @Test
    fun testPart1() {
        val input = readLines("day04/day04_test")
        assertEquals(18, part1(input))
    }

    @Test
    fun testPart2() {
        val input = readLines("day04/day04_test")
        assertEquals(9, part2(input))
    }
}