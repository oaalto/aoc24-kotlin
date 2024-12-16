package day06

class Guard(lines: List<String>) {
    private var direction: Direction = Direction.UP

    var position: Position = lines.foldIndexed(Position(0, 0)) { y, pair, line ->
        val x = line.indexOfFirst { it == '^' }
        if (x == -1) {
            pair
        } else {
            Position(x, y)
        }
    }
        private set

    fun move(): Position {
        position = getNextPosition()
        return position
    }

    fun turn90DegreesRight() {
        direction = direction.turn90DegreesRight()
    }

    fun getNextPosition(): Position {
        return position
            .addX(direction.x)
            .addY(direction.y)
    }
}