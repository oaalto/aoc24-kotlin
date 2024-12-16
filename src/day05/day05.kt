package day05

import println
import readLines

fun main() {
    val lines = readLines("day05/day05")
    part1(lines).println()
    part2(lines).println()
}

fun part1(lines: List<String>): Int {
    val manual = Manual(lines)
    val validUpdates = manual.validUpdates()

    return validUpdates.sumOf { update ->
        val index = update.pages.size / 2
        update.pages[index]
    }
}

fun part2(lines: List<String>): Int {
    val manual = Manual(lines)
    val invalidUpdates = manual.invalidUpdates()

    val fixedUpdates = manual.fixUpdates(invalidUpdates)

    return fixedUpdates.sumOf { update ->
        val index = update.pages.size / 2
        update.pages[index]
    }
}

class Manual(lines: List<String>) {
    private val splitIndex = lines.indexOf("")
    private val rules = lines.subList(0, splitIndex)
        .map { it.split("|") }
        .map { Rule(it[0].toInt(), it[1].toInt()) }
    private val updates = lines.drop(splitIndex + 1)
        .map { it.split(",") }
        .map { Update(it.map(String::toInt)) }

    val findRule = { v1: Int, v2: Int ->
        rules.find { rule ->
            (rule.left == v1 || rule.left == v2) && (rule.right == v1 || rule.right == v2)
        }
    }

    fun validUpdates() = updates.filter { update ->
        update.pages.subList(0, update.pages.size - 1).filterIndexed { index, page ->
            update.pages.subList(index + 1, update.pages.size).all {
                val rule = findRule(page, it)
                if (rule != null) {
                    return@all rule.left == page && rule.right == it
                }

                true
            }
        }.size == update.pages.size - 1
    }

    fun invalidUpdates(): List<Update> {
        val validUpdates = validUpdates()
        return updates.subtract(validUpdates.toSet()).toList()
    }

    fun fixUpdates(updates: List<Update>): List<Update> {
        return updates.map { update ->
            Update(update.pages.sortedWith { left, right ->
                val rule = findRule(left, right)
                if (rule != null) {
                    if (rule.left == left && rule.right == right) {
                        return@sortedWith 1
                    } else if (rule.left == right && rule.right == left) {
                        return@sortedWith -1
                    }
                }

                0
            })
        }
    }
}

data class Rule(val left: Int, val right: Int)
data class Update(val pages: List<Int>)



