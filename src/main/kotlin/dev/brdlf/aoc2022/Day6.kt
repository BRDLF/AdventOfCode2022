package dev.brdlf.aoc2022

const val PART_ONE = 4
const val PART_TWO = 14

class Day6(isTest: Boolean): Day(isTest) {
    override val number: Int
        get ()  = 6

    private val signal: String = inputList[0]

    private fun logic(size: Int): Int {
        val stack = mutableListOf<Char>()
        for (charIndex in signal.indices) {
            stack.add(signal[charIndex])
            if (isPacket(stack, size)) return charIndex + 1
            if (stack.size == size) stack.removeFirst()
        }
        return -1
    }

    private fun isPacket(stack: List<Char>, size: Int): Boolean {
        return stack.size == size &&stack.distinct().size == size
    }

    override fun a() {
        println(logic(PART_ONE))
    }

    override fun b() {
        println(logic(PART_TWO))
    }
}