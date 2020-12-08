package day7

import java.io.File

class Day7 {
    // how many colors can contain at least one shiny gold bag?

    companion object {
        private const val INPUT_FILE = "src/main/kotlin/day7/input.txt"
        private const val SHORT_INPUT_FILE = "src/main/kotlin/day7/shortInput.txt"
        private const val ITEM = "shiny gold bag"
        private const val DELIMITER = " contain"
    }

    private fun getFileContent() = File(INPUT_FILE).useLines { it.toList() }

    private fun createMap(): MutableMap<String, List<String>> {
        val inputMap =
            File(INPUT_FILE).useLines { line -> line.groupBy { it.split(DELIMITER)[0].dropLast(5) } }.toMutableMap()
        val tempContentMap = mutableMapOf<String, String>()
        inputMap.map {
            val itemValue = it.value.toString().split(" contain")[1].dropLast(2).drop(1).split(", ")
            inputMap[it.key] = itemValue
        }

        return inputMap
    }

    private fun getHolders(inputMap: Map<String, List<String>>) {

        val holders = mutableSetOf<String>()
        var directs = inputMap.filter { mapEntry -> mapEntry.value.any { it.contains(ITEM) } }.keys
        holders += directs
        val indirects = mutableSetOf<String>()
        directs.forEach { direct ->
            val indirectItems = inputMap.filter { entry -> entry.value.any { it.contains(direct) } }.keys
            indirects += indirectItems
        }






        println(holders.size)

    }


    fun main() {
        //val input = getFileContent()
        val inputMap = createMap()
        //println(inputMap)
        getHolders(inputMap)

    }
}