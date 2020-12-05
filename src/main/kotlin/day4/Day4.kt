package day4

import java.io.File

class Day4 {

    private fun isValid(passport: Map<String, String>): Boolean {

        passport.forEach {
            when (it.key) {

                // MUSTS = listOf(0"byr", 1"iyr", 2"eyr", 3"hgt", 4"hcl", 5"ecl", 6"pid")

                MUSTS[0] -> if (it.value.length != 4 || it.value.toInt() !in 1920..2002) return false
                MUSTS[1] -> if (it.value.length != 4 || it.value.toInt() !in 2010..2020) return false
                MUSTS[2] -> if (it.value.length != 4 || it.value.toInt() !in 2020..2030) return false

                MUSTS[3] -> {
                    val value: Int = it.value.takeWhile { char -> char.isDigit() }.toInt()
                    val type = it.value.substring(it.value.length - 2, it.value.length)

                    if (type == "cm") {
                        if (value !in 150..193) return false
                    } else if (type == "in") {
                        if (value !in 59..76) return false
                    } else return false
                }

                MUSTS[4] -> {
                    if (it.value[0] != '#') {
                        return false
                    } else if (it.value.length != 7 ||
                        it.value.substring(1, it.value.length).any { char -> !char.isLetterOrDigit() }
                    ) {
                        return false
                    } else return false
                }

                MUSTS[5] -> if (it.value !in potentialEyeColor) return false
                MUSTS[6] -> if (it.value.length != 9) return false
                OPTIONAL[0] -> print(".")
                else -> return true

            }
            return true
        }

        return true
    }


    var counter = 0

    companion object {
        private const val INPUT_FILE_NAME = "src/main/kotlin/day4/input.txt"
        private const val SHORT_INPUT_FILE_NAME = "src/main/kotlin/day4/shortInput.txt"
        private const val VALID = "src/main/kotlin/day4/valid.txt"
        private const val INVALID = "src/main/kotlin/day4/invalid.txt"
        private val MUSTS = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        private val OPTIONAL = listOf("cid")
        private val potentialEyeColor = listOf<String>("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    }

    // Calls a given block callback, giving it a sequence of all the lines in a file by baeldung
    private fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }

    private fun countValidPassports(list: List<String>): Int {
        list.forEach { passport ->
            if (MUSTS.all { must -> passport.contains(must) }) counter++
        }
        return counter
    }

    private fun countValidPassports2(list: List<String>): Int {
        list.forEach { passportString ->
            if (MUSTS.all { must -> passportString.contains(must) }) {
                val words = passportString.split(" ")
                val passportMap = mutableMapOf<String, String>()
                words.map {
                    passportMap[it.substring(0, 3)] = it.substring(4, it.length)
                }

                if (isValid(passportMap)) counter++
            }

        }
        return counter
    }


    private fun getPassportList(fileName: String): List<String> =
        File(fileName).useLines {
            it.toList().joinToString("+").replace("+", " ").split("  ")
        }

    fun main() {

        val passports = getPassportList(INPUT_FILE_NAME)

        println(countValidPassports2(passports))

    }

}