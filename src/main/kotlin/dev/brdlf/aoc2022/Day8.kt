package dev.brdlf.aoc2022

class Day8(isTest: Boolean): Day(isTest) {
    override val number: Int = 8

    var forest: List<List<Int>> = listOf(listOf())

    init {
        val tempForest: MutableList<List<Int>> = mutableListOf()
        for (row in inputList){
            tempForest.add(row.chunked(1).map { it.toIntOrNull()?: throw Exception("Init") })
        }
        forest = tempForest
    }

    private fun lookNorth(row: Int, col: Int): Boolean {
        var nRow = row
        while (nRow > 0) {
            nRow -= 1
            if (forest[nRow][col] >= forest[row][col]) return false
        }
        return true
    }
    private fun lookSouth(row: Int, col: Int): Boolean {
        var nRow = row
        while (nRow < forest.lastIndex) {
            nRow += 1
            if (forest[nRow][col] >= forest[row][col]) return false
        }
        return true
    }
    private fun lookWest(row: Int, col: Int): Boolean {
        var nCol = col
        while (nCol > 0) {
            nCol -= 1
            if (forest[row][nCol] >= forest[row][col]) return false
        }
        return true
    }
    private fun lookEast(row: Int, col: Int): Boolean {
        var nCol = col
        while (nCol < forest[row].lastIndex) {
            nCol += 1
            if (forest[row][nCol] >= forest[row][col]) return false
        }
        return true
    }
    private fun isVisible(row: Int, col: Int): Boolean {
        return (lookNorth(row, col) || lookEast(row, col) || lookWest(row, col) || lookSouth(row, col))
    }

    private fun countNorth(row: Int, col: Int): Int {
        var count = 0
        val nRow: () -> Int = {row - count}
        while (nRow() > 0) {
            count++
            if (forest[nRow()][col] >= forest[row][col]) break
        }
        return count
    }
    private fun countSouth(row: Int, col: Int): Int {
        var count = 0
        val nRow: () -> Int = {row + count}
        while (nRow() < forest.lastIndex) {
            count++
            if (forest[nRow()][col] >= forest[row][col]) break
        }
        return count
    }
    private fun countWest(row: Int, col: Int): Int {
        var count = 0
        val nCol: () -> Int = {col - count}
        while (nCol() > 0) {
            count++
            if (forest[row][nCol()] >= forest[row][col]) break
        }
        return count
    }
    private fun countEast(row: Int, col: Int): Int {
        var count = 0
        val nCol: () -> Int = {col + count}
        while (nCol() < forest[row].lastIndex) {
            count++
            if (forest[row][nCol()] >= forest[row][col]) break
        }
        return count
    }

    private fun scenicScore(row: Int,col: Int): Int {
        return countEast(row, col) * countNorth(row, col) * countSouth(row, col) * countWest(row, col)
    }

    private fun pOne() {
        var counter = 0
        for (row in forest.indices) {
            for (col in forest[row].indices) {
                if (isVisible(row, col)) {
                    counter++
                }
            }
        }
        println(counter)
    }

    private fun pTwo() {
        var topScore = -1
        for (row in forest.indices) {
            for (col in forest[row].indices) {
                val testScore = scenicScore(row, col)
                topScore = if (testScore > topScore) testScore else topScore
            }
        }
        println(topScore)
    }

    override fun a() {
        pOne()
    }

    override fun b() {
        pTwo()
    }

}