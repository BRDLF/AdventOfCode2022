package dev.brdlf.aoc2022

import java.io.File
import java.util.Scanner

interface Day {
    val number: Int
    fun a()
    fun b()
    fun getScanner(): Scanner {
        val file = File("inputs/Day$number")
        return Scanner(file)
    }
}