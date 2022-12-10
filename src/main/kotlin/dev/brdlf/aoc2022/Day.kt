package dev.brdlf.aoc2022

import java.io.File
import java.util.Scanner

abstract class Day(protected val TEST: Boolean = false) {
    abstract val number: Int
    abstract fun a()
    abstract fun b()
    protected val inputList: List<String>
    fun isTesting(): String = if (this.TEST) "(testing) " else ""
    private fun getScanner(): Scanner {
        val file = if (TEST) File("inputs/Test$number") else File("inputs/Day$number")
        return Scanner(file)
    }
    init {
        val sc = getScanner()
        val tempList = mutableListOf<String>()
        while (sc.hasNextLine()){
            tempList.add(sc.nextLine())
        }
        inputList = tempList
    }
}