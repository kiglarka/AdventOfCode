package day5

import java.io.File
import java.util.stream.IntStream.range
import kotlin.streams.toList

class Day5 {

    companion object {
        private const val INPUT_FILE = "src/main/kotlin/day5/input.txt"
        private val ROW_RANGE = Pair(0, 127)
        private val SEAT_RANGE = Pair(0, 7)
    }

    private val boardingPasses : MutableList<BoardingPass> = mutableListOf()

    private fun getMaxId(): Int = boardingPasses.maxOf{ it.seatId }
    private fun getMinId(): Int = boardingPasses.minOf{ it.seatId }

    private fun getMyId(): Int{
        var minId = getMinId()
        boardingPasses.sortedBy { it.seatId }.forEach {
            if (minId != it.seatId) return minId
            minId++
        }
        return minId
    }

    private fun onBoardPasses(){
        getInputList().forEach {line ->
            val seat = adjustRange(line)
            val seatId = generateSeatId(seat)
            boardingPasses.add(BoardingPass(seat,seatId))
        }
    }

    private data class BoardingPass(val seat: Pair<Int,Int>, val seatId: Int)

    private fun adjustRange(code: String) : Pair<Int,Int>{
        var rowRange = ROW_RANGE
        var seatRange = SEAT_RANGE

        code.forEach { char ->
            val rowDiff = (rowRange.second - rowRange.first)
            val seatDiff = (seatRange.second - seatRange.first)
            val rowMedium = rowDiff / 2
            val seatMedium = seatDiff / 2

            when (char) {
                'F' -> rowRange = Pair(rowRange.first, rowRange.first+rowMedium)
                'B' -> rowRange = Pair(rowRange.second-rowMedium, rowRange.second)
                'L' -> seatRange = Pair(seatRange.first, seatRange.first+seatMedium)
                'R' -> seatRange = Pair(seatRange.second-seatMedium, seatRange.second)
            }
        }
        return Pair(rowRange.first,seatRange.first)
    }

    private fun generateSeatId(seat: Pair<Int,Int>): Int = seat.first * 8 + seat.second

    private fun getInputList(): List<String> = File(INPUT_FILE).useLines { it.toList() }


    fun main() {
        onBoardPasses()
        println(getMyId())
    }
}


