package day07

import readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {
    @Test
    fun testPart1() {
        val input = readLines("day07/day07_test")
        assertEquals(3749, runner(input))
    }

    @Test
    fun testPart2() {
        val input = readLines("day07/day07_test")
        assertEquals(11387, runner(input))
    }
}