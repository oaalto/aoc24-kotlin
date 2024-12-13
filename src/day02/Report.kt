package day02

import kotlin.math.abs

data class Report(val levels: List<Long>) {
    fun isValid(): Boolean {
        return isGradual() && (isDescending() || isAscending())
    }

    fun isDescending(): Boolean {
        val sortedLevels = levels.sortedDescending()
        return sortedLevels == levels
    }

    fun isAscending(): Boolean {
        val sortedLevels = levels.sorted()
        return sortedLevels == levels
    }

    fun isGradual(): Boolean {
        return levels.windowed(2).all { (left, right) ->
            val diff = abs(left - right)
            diff > 0 && diff < 4
        }
    }
}