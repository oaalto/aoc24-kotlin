package day04

class CrossedXmasMap(private val width: Int, private val height: Int, input: List<String>) {
    private val map = input.map(String::trim).flatMap { it.toCharArray().asList() }
    private val aLocations = map
        .mapIndexed { index, c -> if (c == 'A') index else -1 }
        .filter { i -> i != -1 }

    private val MAS = "MAS"
    private val MAS_LENGTH = MAS.length

    private inner class Position(val x: Int, val y: Int) {
        fun addX(x: Int): Position {
            return Position(this.x + x, y)
        }

        fun addY(y: Int): Position {
            return Position(x, this.y + y)
        }

        fun contain(c: Char): Boolean {
            val index = xyToIndex(x, y)
            if (index >= 0 && index < map.size) {
                return map[index] == c
            }
            return false
        }
    }

    fun check(): Int {
        return aLocations.fold(0) { acc, loc ->
            val center = indexToXY(loc)
            val nw = center.addX(-1).addY(-1)
            val ne = center.addX(1).addY(-1)
            val se = center.addX(1).addY(1)
            val sw = center.addX(-1).addY(1)
            val neswChecks = listOf({ checkNE(sw) }, { checkSW(ne) })
            val nwseChecks = listOf({ checkNW(se) }, { checkSE(nw) })
            val checks = listOf(
                { neswChecks.any { c -> c() } },
                { nwseChecks.any { c -> c() } }
            )

            acc + checks.all { c -> c() }.compareTo(false)
        }
    }

    private fun indexToXY(index: Int): Position {
        val x: Int = index % width
        val y: Int = index / width
        return Position(x, y)
    }

    private fun xyToIndex(x: Int, y: Int): Int {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return -1
        }
        return y * width + x
    }

    private fun checkNW(pos: Position): Boolean {
        return MAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(-i).addX(-i)
            p.contain(c)
        }.size == MAS_LENGTH
    }

    private fun checkSW(pos: Position): Boolean {
        return MAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(i).addX(-i)
            p.contain(c)
        }.size == MAS_LENGTH
    }

    private fun checkSE(pos: Position): Boolean {
        return MAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(i).addX(i)
            p.contain(c)
        }.size == MAS_LENGTH
    }

    private fun checkNE(pos: Position): Boolean {
        return MAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(-i).addX(i)
            p.contain(c)
        }.size == MAS_LENGTH
    }
}