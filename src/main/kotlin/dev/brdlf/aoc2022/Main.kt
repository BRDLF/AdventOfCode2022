package dev.brdlf.aoc2022

import java.io.FileNotFoundException

//TODO: Update as needed
const val MAX_DAY = 4

fun main(args: Array<String>) {
    try{
        run(args.cleanArgs())
    }
    catch (e: FileNotFoundException) {
        println(e.message)
    }
}

fun run(args: Array<String?>) {
    val day = selectDay(args[0])?: return
    val part = selectPart(args[1])?: return
    print("The answer to Day ${day.number} part $part is: ")
    when(part){
        "One" -> day.a()
        "Two" -> day.b()
    }
}

private fun Array<String>.cleanArgs(): Array<String?> {
    val output = arrayOf<String?>(null, null)
    if (this.isEmpty()) return output
    for (n in 0 until kotlin.math.max(this.size, 2)) output[n] = this[n]
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
            "exit" -> null
            else -> continue
        }
    }
}

fun selectDay(givenDay: String? = null): Day? {
    //TODO: Update as needed
    val day = inputDay(givenDay)?: return null
    return when(day.toIntOrNull()) {
        1 -> Day1()
        2 -> Day2()
        3 -> Day3()
        4 -> Day4()
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