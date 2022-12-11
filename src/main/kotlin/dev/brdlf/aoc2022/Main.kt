package dev.brdlf.aoc2022

import java.io.FileNotFoundException

//TODO: Update as needed
const val MAX_DAY = 11

fun main(args: Array<String>) {
    try{
        run(args.cleanArgs())
    }
    catch (e: FileNotFoundException) {
        println(e.message)
    }
}

fun run(args: Map<String, String>) {
    val day = selectDay(args["-Day"], args["-Test"].toBoolean())?: return
    val part = selectPart(args["-Part"])?: return
    print("The answer to Day ${day.number} part $part ${day.isTesting()}is: ")
    when(part){
        "One" -> day.a()
        "Two" -> day.b()
        "Both" -> {
            print("\nPart One: ")
            day.a()
            print("Part Two: ")
            day.b()
        }
    }
}

private fun Array<String>.cleanArgs(): Map<String, String> {
    val output = mutableMapOf<String, String>()
    for (item in this){
        if (!item.startsWith("-")) continue
        val index = this.indexOf(item)
        if (index == -1 || index == this.lastIndex) continue
        if (this[index+1].startsWith("-")) continue
        output.put(item, this[index+1])
    }
    return output
}

fun selectPart(arg: String? = null): String? {
    var part = arg
    while (true) {
        part?:run {
            println("Please select part (One or Two):")
            part = readln().trim()
        }
        return when(part!!.lowercase()) {
            "a", "1", "one" -> "One"
            "b", "2", "two" -> "Two"
            "c", "3", "both" -> "Both"
            "exit" -> null
            else -> continue
        }
    }
}

//TODO: Update as needed
fun selectDay(givenDay: String? = null, isTest: Boolean = false): Day? {
    val day = inputDay(givenDay)?: return null
    return when(day.toIntOrNull()) {
        1 -> Day1(isTest)
        2 -> Day2(isTest)
        3 -> Day3(isTest)
        4 -> Day4(isTest)
        5 -> Day5(isTest)
        6 -> Day6(isTest)
        7 -> Day7(isTest)
        8 -> Day8(isTest)
        9 -> Day9(isTest)
        10 -> Day10(isTest)
        11 -> Day11(isTest)
        else -> throw Exception("Unexpected exit from selectDay")
    }
}

fun inputDay(givenDay: String? = null): String? {
    var day = givenDay
    while(true){
        day?:run {
            println("Select a day:")
            (1..MAX_DAY).forEach{ print("$it, ") }
            println("or EXIT to exit")
            day = readln().trim()
        }
        when {
            (day?.toIntOrNull() in 1..MAX_DAY) -> return day
            day == "EXIT" -> return null
            else -> println("The day provided is unavailable.")
        }
    }
}