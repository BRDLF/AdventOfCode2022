package dev.brdlf.aoc2022

class Day2(isTest: Boolean): Day(isTest) {
    override val number: Int = 2

    companion object{
        private val abstractionMap = mapOf("A" to 0, "B" to 1, "C" to 2, "X" to 0, "Y" to 1, "Z" to 2)
    }

    private fun lineToPair(line: String): Pair<Int, Int> {
        val first = abstractionMap[line.split(" ")[0]]?: throw Exception()
        val second = abstractionMap[line.split(" ")[1]]?: throw Exception()
        return Pair(first, second)
    }
    private fun Int.win(): Int = (this+1).mod(3) + 7
    private fun Int.tie(): Int = this + 4
    private fun Int.lose(): Int = (this+2).mod(3) + 1
    private val p1: (Pair<Int, Int>) -> Int = {
        when(it.second - it.first){
            0 -> 4 + it.second// tie
            -1, 2 -> 1 + it.second// lose
            -2, 1 -> 7 + it.second// win
            else -> throw Exception("part1")
        }
    }
    private val p2: (Pair<Int, Int>) -> Int = {
        when(it.second){
            0 -> it.first.lose()
            1 -> it.first.tie()
            2 -> it.first.win()
            else -> throw Exception("part2")
        }
    }

    private fun logic(part: (Pair<Int, Int>) -> Int) {
        var total = 0
        for (match in inputList) {
            total += part(lineToPair(match))
        }
        println(total)
    }

    override fun a() {
        logic(p1)
    }

    override fun b() {
        logic(p2)
    }
}