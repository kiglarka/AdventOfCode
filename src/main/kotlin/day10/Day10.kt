package day10

import java.io.File

class Day10 {

    // the charging outlet near your seat produces the wrong number of jolts
    // you make a list of all of the joltage adapters in your bag.
    // Each of your joltage adapters is rated for a specific output joltage (your puzzle input)
    // Any given adapter can take an input 1, 2, or 3 jolts lower than its rating
    // your device has a built-in joltage adapter rated for 3 jolts higher than the highest-rated adapter in your bag.
    // Treat the charging outlet near your seat as having an effective joltage rating of 0.
    // If you use every adapter in your bag at once, what is the distribution of joltage differences between the
    // charging outlet, the adapters, and your device?
    // input max + 3 = your device built-in joltage adapter rating

    companion object {
        private const val INPUT_FILE = "src/main/kotlin/day10/input.txt"
    }

    private var actual = 0
    private var max = 0


    private fun getListFromInput(file: String) = File(file).useLines { it.toList() }
    private fun getSorted(list: List<String>) = list.map { it.toInt() }.sorted()

    private fun calculateAdapters(set: List<Int>): Int {

        var diffOneCounter = 0
        var diffThreeCounter = 0

        set.map {
            when (it - actual) {
                1 -> diffOneCounter++
                3 -> diffThreeCounter++
                else -> println("nem stimmt")
            }
            actual = it
        }

        return diffOneCounter * (diffThreeCounter + 1)
    }


    fun main() {
        val set = getSorted(getListFromInput(INPUT_FILE))
        max = set.last().toInt()
        val builtAdapter = max + 3
        calculateAdapters(set)

    }
}