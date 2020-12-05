package day2

import java.io.File

class Day2 {

// list of passwords as input
// password policy
// 1-3 a -> lowest and highest number of times the given letter must appear
// QUESTION: How many passwords are valid

    private fun checkPosition(password: String, index: Int, char: Char): Boolean = password[index] == char

    companion object {
        private const val INPUT_FILE_NAME = "src/main/kotlin/day2/input.txt"
    }

    private fun getInputList() = File(INPUT_FILE_NAME).useLines { it.toList() }

    private fun checkNewPolicy(item: String): Boolean {
        val list = item.split(" ")
        val index1 = list.first().split("-").first().toInt() - 1
        val index2 = list.first().split("-").last().toInt() - 1
        val letter = list[1].first()
        val password = list.last()

        val pair = Pair(
            checkPosition(password, index1, letter),
            checkPosition(password, index2, letter),
        )

        return pair.first != pair.second
    }

    private fun checkPassword(item: String): Boolean {

        val list = item.split(" ")
        val min = list.first().split("-").first().toInt()
        val max = list.first().split("-").last().toInt()
        val letter = list.get(1).first()
        val password = list.last()

        val counter = password.count { it == letter }

        return (counter in (min..max))
    }

    private fun countCorrect(list: List<String>): Int =
        list.count { checkPassword(it) }

    private fun countNewCorrect(list: List<String>): Int =
        list.count { checkNewPolicy(it) }


    fun main() {
        val list = getInputList()
        //println(countCorrect(list))
        println(countNewCorrect(list))
    }
}