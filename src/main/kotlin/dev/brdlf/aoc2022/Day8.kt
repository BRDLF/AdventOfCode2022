package dev.brdlf.aoc2022

class Day8(isTest: Boolean): Day(isTest) {
    data class cord(val row: Int, val col: Int)
    override val number: Int
        get () = 8

    private var forest: List<List<Int>> = listOf(listOf())

    init {
        val tempForest: MutableList<List<Int>> = mutableListOf()
        for (row in inputList){
            tempForest.add(row.chunked(1).map { it.toIntOrNull()?: throw Exception("Init") })
        }
        forest = tempForest
    }

    private val greaterOrEqual: (cord, cord) -> Boolean = { new, old -> forest[new.row][new.col] >= forest[old.row][old.col]}

    private val cordRI: (cord, Int) -> cord = { old, n -> cord(old.row + n, old.col) }
    private val cordRD: (cord, Int) -> cord = { old, n -> cord(old.row - n, old.col) }
    private val cordCI: (cord, Int) -> cord = { old, n -> cord(old.row, old.col + n) }
    private val cordCD: (cord, Int) -> cord = { old, n -> cord(old.row, old.col - n) }

    private fun cord.inRange(): Boolean = (this.row in 1 until forest.lastIndex && this.col in 1 until forest[row].lastIndex)

    private fun cord.score(incDec: (cord, Int) -> cord): Int {
        var count = 0
        val newCord: () -> cord = {incDec(this, count)}
        while (newCord().inRange()) {
            count++
            if (greaterOrEqual(newCord(), this)) break
        }
        return count
    }

    private fun cord.look(incDec: (cord, Int) -> cord): Int {
        var count = 0
        val newCord: () -> cord = {incDec(this, count)}
        while (newCord().inRange()) {
            count ++
            if (greaterOrEqual(newCord(), this)) return -1
        }
        return count
    }

    private fun visibleCord(row: Int, col: Int): Int {
        val cord = cord(row, col)
        val cD = cord.look(cordCD) //Up
        val rD = cord.look(cordRD) //Lt
        val rI = cord.look(cordRI) //Rt
        val cI = cord.look(cordCI) //Dn

        return if (cD >= 0 || rD >= 0 || rI >= 0 || cI >= 0) 1 else 0
    }

    private fun scoreCord(row: Int, col: Int): Int {
        val cord = cord(row, col)
        val cD = cord.score(cordCD) //Up
        val rD = cord.score(cordRD) //Lt
        val rI = cord.score(cordRI) //Rt
        val cI = cord.score(cordCI) //Dn

        return rI * rD * cI * cD
    }

    private fun pOne() {
        var counter = 0
        for (row in forest.indices) {
            for (col in forest[row].indices) {
                if (visibleCord(row, col) == 1) {
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
                val testScore = scoreCord(row, col)
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