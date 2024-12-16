package day06

class Map(lines: List<String>) {
    private val width = lines[0].length
    private val height = lines.size

    private val tiles = lines.flatMapIndexed { y, line ->
        line.mapIndexed { x, char ->
            when (char) {
                '#' -> Wall(x, y)
                else -> Floor(x, y)
            }
        }
    }

    private var guard = Guard(lines)

    private val history = mutableSetOf(guard.position)

    fun simulate(): Int {
        while (!isOutSide()) {
            if (isBlocked(guard.getNextPosition())) {
                guard.turn90DegreesRight()
            }

            val guardPos = guard.move()
            history.add(guardPos)
        }

        return history.size
    }

    private fun isBlocked(position: Position): Boolean {
        val tile = positionToTile(position)
        return tile is Wall
    }

    private fun isOutSide(): Boolean {
        val position = guard.getNextPosition()
        return position.x < 0 || position.x >= width || position.y < 0 || position.y >= height
    }

    private fun xyToIndex(x: Int, y: Int): Int {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return -1
        }
        return y * width + x
    }

    private fun positionToTile(position: Position): Tile {
        val index = xyToIndex(position.x, position.y)
        return tiles[index]
    }
}

sealed class Tile(val x: Int, val y: Int)
class Wall(x: Int, y: Int) : Tile(x, y)
class Floor(x: Int, y: Int) : Tile(x, y)