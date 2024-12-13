package day03

import read
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {
    @Test
    fun testPart1() {
        val input = read("day03/day03_test1")
        assertEquals(161, part1(input))
    }

    @Test
    fun testPart2() {
        val input = read("day03/day03_test2")
        assertEquals(48, part2(input))
    }
}