package dev.brdlf.aoc2022

import java.io.File
import java.util.Scanner

abstract class Day(private val test: Boolean = false) {
    abstract val number: Int
    abstract fun a()
    abstract fun b()
    protected val inputList: List<String>
    fun isTesting(): String = if (this.test) "(testing) " else ""
    fun getScanner(): Scanner {
        val file = if (test) File("inputs/Test$number") else File("inputs/Day$number")
        return Scanner(file)
    }
    init {
        val sc = getScanner()
        val inList = mutableListOf<String>()
        while (sc.hasNextLine()){
            inList.add(sc.nextLine())
        }
        inputList = inList
    }
}