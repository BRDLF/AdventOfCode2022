package dev.brdlf.aoc2022

import kotlin.math.abs

class Day9(isTest: Boolean): Day(isTest) {
    override val number: Int
        get() = 9

    private val hasVisited: MutableSet<Coordinate>  = mutableSetOf()

    private fun resetVisitList() {
        hasVisited.clear()
        hasVisited.add(Coordinate(0, 0))
    }

    data class Coordinate(var row: Int, var col: Int) {
        private fun distanceHorizontal(target: Coordinate): Int = (target.col - this.col)
        private fun distanceVertical(target: Coordinate): Int = (target.row - this.row)

        fun adjust(target: Coordinate) {
            while (!this.isTouching(target)) {
                val horizontalDistance = this.distanceHorizontal(target)
                val verticalDistance = this.distanceVertical(target)
                this.row += verticalDistance.coerceIn(-1,1)
                this.col += horizontalDistance.coerceIn(-1,1)
//
            }
        }

        fun moveD() { row++ }
        fun moveU() { row-- }
        fun moveL() { col-- }
        fun moveR() { col++ }
        fun isTouching(head: Coordinate): Boolean {
            val lr = abs(distanceHorizontal(head)) <= 1
            val ud = abs(distanceVertical(head)) <= 1
            return lr && ud
        }
    }

    private fun move(line: String, rope: List<Coordinate>): List<Coordinate> {
        val (direction, distance) = line.split(" ")
        repeat(distance.toInt()) {
            when (direction) {
                "U" -> rope.first().moveU()
                "D" -> rope.first().moveD()
                "L" -> rope.first().moveL()
                "R" -> rope.first().moveR()
                else -> throw Exception()
            }
            for (index in 1 until rope.size) {
                val knot = rope[index]
                val target = rope[index-1]

                if (!knot.isTouching(target)) {
                    knot.adjust(target)
                    if (index == rope.lastIndex) {
                        hasVisited.add(knot.copy())
                    }
                }
            }
        }
        return rope
    }

    private fun logic(size: Int) {
        resetVisitList()
        val rope = List(size) {Coordinate(0, 0)}
        for (line in inputList) {
            move(line, rope)
        }
        println(hasVisited.size)
    }

    override fun a() {
        logic(2)
    }

    override fun b() {
        logic(10)
    }
}