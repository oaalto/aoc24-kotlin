package day04

class XmasMap(private val width: Int, private val height: Int, input: List<String>) {
    internal val map = input.map(String::trim)
        .flatMap { it.toCharArray().asList() }
    private val xLocations = map
        .mapIndexed { index, c -> if (c == 'X') index else -1 }
        .filter { i -> i != -1 }
    private val checks = listOf(
        ::checkNW, ::checkSE, ::checkSW, ::checkNE, ::checkVerticalUp,
        ::checkVerticalDown, ::checkHorizontal, ::checkHorizontalReversed
    )

    private val XMAS = "XMAS"
    private val XMAS_LENGTH = XMAS.length

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
        return checks.fold(0) { acc, check ->
            acc + xLocations.filter { x -> check(x) }.size
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

    private fun checkNW(index: Int): Boolean {
        val pos = indexToXY(index)

        return XMAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(-i).addX(-i)
            p.contain(c)
        }.size == XMAS_LENGTH
    }

    private fun checkSW(index: Int): Boolean {
        val pos = indexToXY(index)

        return XMAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(i).addX(-i)
            p.contain(c)
        }.size == XMAS_LENGTH
    }

    private fun checkSE(index: Int): Boolean {
        val pos = indexToXY(index)

        return XMAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(i).addX(i)
            p.contain(c)
        }.size == XMAS_LENGTH
    }

    private fun checkNE(index: Int): Boolean {
        val pos = indexToXY(index)

        return XMAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(-i).addX(i)
            p.contain(c)
        }.size == XMAS_LENGTH
    }

    private fun checkVerticalUp(index: Int): Boolean {
        val pos = indexToXY(index)

        return XMAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(-i)
            p.contain(c)
        }.size == XMAS_LENGTH
    }

    private fun checkVerticalDown(index: Int): Boolean {
        val pos = indexToXY(index)

        return XMAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addY(i)
            p.contain(c)
        }.size == XMAS_LENGTH
    }

    private fun checkHorizontal(index: Int): Boolean {
        val pos = indexToXY(index)

        return XMAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addX(i)
            p.contain(c)
        }.size == XMAS_LENGTH
    }

    private fun checkHorizontalReversed(index: Int): Boolean {
        val pos = indexToXY(index)

        return XMAS.toCharArray().filterIndexed { i, c ->
            val p = pos.addX(-i)
            p.contain(c)
        }.size == XMAS_LENGTH
    }
}