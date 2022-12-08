package dev.brdlf.aoc2022

import java.lang.Exception

class Day4(isTest: Boolean): Day(isTest) {
    override val number: Int = 4

    private fun String.toRangeList(): List<Int> {
        val (start, end) = this.split("-").map{ it.toIntOrNull()?: throw Exception("Unable to parse int from $this") }
        return IntRange(start, end).toList()
    }

    private val partOne: (List<Int>, List<Int>) -> Boolean = {foo, bar -> foo.containsAll(bar)}
    private val partTwo: (List<Int>, List<Int>) -> Boolean = {foo, bar -> foo.containsAny(bar)}

    private fun List<Int>.containsAny(bar: List<Int>): Boolean {
        for (b in bar) {
            if (this.contains(b)) return true
        }
        return false
    }

    private fun logic(input: List<String>, myFun: (List<Int>, List<Int>) -> Boolean) {
        var counter = 0
        for (pair in input){
            val (fooElf, barElf) = pair.split(",").map { it.toRangeList() }
            if (myFun(fooElf, barElf) || myFun(barElf, fooElf)) counter++
        }
        println(counter)
    }

    override fun a() {
        logic(inputList, partOne)
    }

    override fun b() {
        logic(inputList, partTwo)
    }


}