package dev.brdlf.aoc2022

import java.util.Scanner

class Day1: Day {
    override val number: Int
        get() = 1

    private val calMap: MutableList<Int> = mutableListOf<Int>()

    init{
        val sc: Scanner = getScanner()
        var calorieCount = 0
        while(sc.hasNext()) {
            val buffer = sc.nextLine().toIntOrNull()

            if (buffer==null) {
                calMap.add(calorieCount)
                calorieCount = 0
            }
            else calorieCount += buffer
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