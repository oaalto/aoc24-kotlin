package day04

import readLines
import kotlin.test.Test
import kotlin.test.assertEquals

class XmasMapTest {
    @Test
    fun testXmasMapHorizontal() {
        val data = listOf("XMAS")
        val map = XmasMap(4, 1, data)

        assertEquals(1, map.check())
    }

    @Test
    fun testXmasMapHorizontalReversed() {
        val data = listOf("XMAS".reversed())
        val map = XmasMap(4, 1, data)

        assertEquals(1, map.check())
    }

    @Test
    fun testXmasMapVerticalDown() {
        val data = listOf("X", "M", "A", "S")
        val map = XmasMap(1, 4, data)

        assertEquals(1, map.check())
    }

    @Test
    fun testXmasMapVerticalUp() {
        val data = listOf("X", "M", "A", "S").reversed()
        val map = XmasMap(1, 4, data)

        assertEquals(1, map.check())
    }

    @Test
    fun testXmasMapNE() {
        val data = listOf("...S", "..A.", ".M..", "X...")
        val map = XmasMap(4, 4, data)

        assertEquals(1, map.check())
    }

    @Test
    fun testXmasMapSE() {
        val data = listOf("X...", ".M..", "..A.", "...S")
        val map = XmasMap(4, 4, data)

        assertEquals(1, map.check())
    }

    @Test
    fun testXmasMapSW() {
        val data = listOf("...X", "..M.", ".A..", "S...")
        val map = XmasMap(4, 4, data)

        assertEquals(1, map.check())
    }

    @Test
    fun testXmasMapNW() {
        val data = listOf("S...", ".A..", "..M.", "...X")
        val map = XmasMap(4, 4, data)

        assertEquals(1, map.check())
    }

    @Test
    fun testXmasMap() {
        val data = readLines("day04/day04_test")
        val map = XmasMap(data[0].length, data.size, data)

        val reconstructedData = map.map.windowed(data[0].length, data[0].length)
            .map { chars -> chars.joinToString(separator = "") }

        assertEquals(data, reconstructedData)
    }
}