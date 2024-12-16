package day06

sealed class Direction(val x: Int, val y: Int) {
    data object UP : Direction(0, -1)
    data object RIGHT : Direction(1, 0)
    data object DOWN : Direction(0, 1)
    data object LEFT : Direction(-1, 0)

    fun turn90DegreesRight(): Direction {
        return when (this) {
            UP    -> RIGHT
            RIGHT -> DOWN
            DOWN  -> LEFT
            LEFT  -> UP
        }
    }
}