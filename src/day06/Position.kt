package day06

data class Position(val x: Int, val y: Int) {
    fun addX(x: Int): Position {
        return Position(this.x + x, y)
    }

    fun addY(y: Int): Position {
        return Position(x, this.y + y)
    }
}
