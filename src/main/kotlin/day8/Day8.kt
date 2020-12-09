package day8

import java.io.File

class Day8 {

    companion object {
        private const val INPUT_FILE = "src/main/kotlin/day8/input.txt"
    }

    data class Instruction(val index: Int, val operation: String, val argument: Int)

    private fun getListFromInput() : List<String> = File(INPUT_FILE).useLines { it.toList() }

    private fun getPairs(list: List<String>): MutableList<Instruction> {

        val instructions = mutableListOf<Instruction>()

        list.forEachIndexed{ index, value ->
            val innerList = value.split(" ")
            val operation = innerList.first()
            val argument = innerList.last().toInt()
            instructions.add(Instruction(index,operation,argument))
        }
        return instructions
    }

    class Game(val instructions: List<Instruction>){
        private var nextIndex = 0
        private var accumulator = 0
        private val beenThere = mutableListOf<Int>()

        fun oneStep(instruction: Instruction){
            when (instruction.operation){
                "nop" -> { nextIndex++ }
                "acc" -> {
                    accumulator += instruction.argument
                    nextIndex++
                }
                "jmp" -> {
                    nextIndex += instruction.argument

                }
            }
        }

        fun play(): Int {

            while(!beenThere.contains(nextIndex)){
                beenThere.add(nextIndex)
                oneStep(instructions[nextIndex])
            }
            return accumulator
        }
    }

    fun main(){
        val instructions = getPairs(getListFromInput())
        val game = Game(instructions)
        println(game.play())

    }
}