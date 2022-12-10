package dev.brdlf.aoc2022

import kotlin.Exception

class Day10(isTest: Boolean): Day(isTest) {
    override val number: Int
        get() = 10

    private var X = 1
    private var cycle = 0
    private var total = 0
    private fun resetValues() {
        X = 1
        cycle = 0
        total = 0
    }
    private var screen: CharSequence = ""

    private val p1TotalAdd: () -> Unit = {
        if ((cycle + 20) % 40 == 0) {
            if (TEST) println("cycleCheck: Adding ${cycle}*${X}:${cycle*X} to $total")
            total = total.plus(cycle*X)
            if (TEST) println("New totale is $total")
        }
    }

    private val p2PixelAmmend: () -> Unit = {
        screen = if ((cycle-1)%40 in X-1..X+1) {
            "$screen#"
        } else "$screen."
    }

    fun noop(op: () -> Unit) {
        cycle++
        if (TEST) printCycleInfo()
        op()
    }
    fun addX(s: String, op: () -> Unit) {
        cycle ++
        op()
        if (TEST) printCycleInfo()
        cycle ++
        op()
        if (TEST) {
            printCycleInfo()
            println("Adding ${s.toInt()} to $X")
        }
        X += s.toIntOrNull()?: throw Exception("addX invalid add")
        if (TEST) printSpritePosition()
    }

    fun printSpritePosition() {
        print("%16s: ".format("Sprite position"))
        for (pixel in 0..40) {
            print(if (pixel in X-1..X+1) "#" else ".")
        }
        println("\n")
    }

    private fun printCycleInfo() {
        println("%16s: Cycle: %d Sprite: %d".format("Info", cycle, X))
        print("%16s: ".format("Current CRT row"))
        println(screen)
    }

    private fun part(op: () -> Unit){
        resetValues()
        for (line in inputList) {
            val n = line.split(" ")
                when (n.first()) {
                    "noop" -> noop(op)
                    "addx" -> addX(n[1], op)
                    else -> throw Exception("Unexpected function")
                }
        }
        println(total)
    }

    override fun a() {
        part(op = p1TotalAdd)
    }

    override fun b() {
        println()
        part(op = p2PixelAmmend)
        screen.chunked(40).forEach{ println(it) }
    }
}