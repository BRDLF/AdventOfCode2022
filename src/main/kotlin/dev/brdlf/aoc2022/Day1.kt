package dev.brdlf.aoc2022

class Day1(isTest: Boolean): Day(isTest) {
    override val number: Int
        get ()  = 1

    private val calMap: MutableList<Int> = mutableListOf<Int>()

    init{
        var calorieCount = 0
        for (row in inputList) {
            val lineValue = row.toIntOrNull()
            if (lineValue==null) {
            lineValue?: calMap.add(calorieCount)
                calorieCount = 0
            }
            else {
                calorieCount += lineValue
            }
        }
        calMap.sortDescending()
    }

    override fun a() {
        println(calMap.first())
    }

    override fun b() {
        println(calMap.take(3).sum())
    }
}