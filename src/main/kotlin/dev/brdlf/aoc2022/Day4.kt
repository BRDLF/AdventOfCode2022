package dev.brdlf.aoc2022

import java.lang.Exception

class Day4: Day {
    override val number: Int
        get() = 4

    private val test = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8",
    )
    private val elfPairs: MutableList<String> = mutableListOf()

    init {
        val sc = getScanner()
        while (sc.hasNextLine()) {
            elfPairs.add(sc.nextLine())
        }
    }

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
        logic(elfPairs, partOne)
    }

    override fun b() {
        logic(elfPairs, partTwo)
    }


}