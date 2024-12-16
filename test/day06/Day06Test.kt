package day06

import readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {
    @Test
    fun testPart1() {
        val input = readLines("day06/day06_test")
        assertEquals(41, part1(input))
    }

    /*@Test
    fun testPart2() {
        val input = readLines("day05/day05_test")
        assertEquals(123, part2(input))
    }*/
}