package day1

import java.io.File

class Day1 {

    // gold: starfish -> stars
// needs 50 "stars" by 25th of December
// collect start by solving the puzzles
// 2 puzzles each day

// Exercise 1:
// find two numbers that sum to 2020
// Result: multiply them

    private fun findTriplet(list: List<Int>): Int {
        // create set
        val setter: MutableSet<Pair<Int, Int>> = mutableSetOf()

        val i = 0
        val j = i + 1

        for (item in i until list.size) {
            for (anotherItem in j until list.size - 1) {
                setter.add(Pair(list[item], list[anotherItem]))
            }
        }

        var x: Int

        setter.forEach {
            x = 2020 - it.first - it.second
            if (x in list && it.first + it.second + x == 2020) return x * it.first * it.second
        }

        return 0
    }

    private fun pairToTwenty(list: List<Int>): Pair<Int, Int> {

        list.forEach {
            if (2020 - it in list) {
                return Pair(it, 2020 - it)
            }
        }
        return Pair(0, 0)
    }

    private fun multiply(pair: Pair<Int, Int>): Int = pair.first * pair.second

    companion object {
        private const val INPUT_FILE_NAME = "src/main/kotlin/day1/input.txt"
    }

    private fun getIntListFromInput() = File(INPUT_FILE_NAME).useLines { it.toList() }.map { it.toInt() }


    fun main() {


        val list = getIntListFromInput()
        // println(multiply(sumToZero(list)))
        println(findTriplet(list))
    }
}
