package day3

import java.io.File
import java.util.stream.IntStream.range

class Day3 {

    companion object {
        private const val INPUT_FILE = "src/main/kotlin/day3/input.txt"
    }

    private val inputList = File(INPUT_FILE).useLines { it.toList() }

    private data class Toboggan(var y: Int = 0, var x: Int = 0, var trees: Int = 0, val xDelta: Int, val yDelta: Int)

    private fun move(toboggan: Toboggan){
            toboggan.y += toboggan.yDelta
            toboggan.x = (toboggan.x + toboggan.xDelta) % 31
            if (inputList[toboggan.y][toboggan.x] == '#') toboggan.trees++
        }

    private fun moveTilTheBottom(toboggan: Toboggan){
        while (toboggan.y != inputList.size-1){
            move(toboggan)
        }
    }

    /*
    private fun checkLength(){
        // x
        println(inputList.size)
        // y
        println(inputList[0].length)
    }

     */

    fun main(){
        //checkLength()
        val toboggans = mutableListOf<Toboggan>()
        toboggans.add(Toboggan(xDelta = 1, yDelta = 1))
        toboggans.add(Toboggan(xDelta = 3, yDelta = 1))
        toboggans.add(Toboggan(xDelta = 5, yDelta = 1))
        toboggans.add(Toboggan(xDelta = 7, yDelta = 1))
        toboggans.add(Toboggan(xDelta = 1, yDelta = 2))

        toboggans.forEach {
            moveTilTheBottom(it)
        }

        var result : Long = 1
        toboggans.forEach {
            result *= it.trees }

        println(result)

    }


}