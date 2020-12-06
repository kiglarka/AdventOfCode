package day6

import java.io.File
import kotlin.streams.toList

class Day6 {

    companion object {
        private const val INPUT_FILE = "src/main/kotlin/day6/input.txt"
        private const val SHORT_INPUT_FILE = "src/main/kotlin/day6/shortInput.txt"
    }

    private var answerList = File(INPUT_FILE).useLines {
        it.toList().joinToString("+").replace("+", " ").split("  ")
    }

    private fun getSumOfUniqueAnswers(): Int {
        var sum = 0
        answerList.forEach { groupAnswer ->
            val set = mutableSetOf<Char>()
            groupAnswer.forEach { answer: Char -> if (answer != ' ') set.add(answer) }
            sum += set.count()
        }
        return sum
    }

    private fun getCommonAnswers(): Int {
        var sum = 0
        // [[syz, cywjs, jptzy, pjkyr, nhdbvyoqlxm], [sljgqfuvrkpzhbax, balxvkgjquhzpsrf, jghpasuerbfvlkxiz]]
        val nestedAnswers = answerList.map { it.split(" ") }

        for (index in nestedAnswers.indices) {
            val person = nestedAnswers[index].size
            val map: Map<Char, Int> = answerList[index].replace(" ", "").groupingBy { it }.eachCount()
            //println(map)
            sum += map.filter { it.value == person }.count()
        }

        return sum
    }

    fun main() {
        //println(answerList)
        //val summa = getSumOfUniqueAnswers()
        val summa = getCommonAnswers()
        println(summa)

    }
}