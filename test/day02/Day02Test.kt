package day02

import readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun testPart1() {
        val input = readLines("day02/day02_test")
        assertEquals(2, part1(input))
    }

    @Test
    fun testPart2() {
        val input = readLines("day02/day02_test")
        assertEquals(4, part2(input))
    }
}