package dev.brdlf.aoc2022

class Day5(isTest: Boolean): Day(isTest) {
    override val number: Int = 5

    private lateinit var yard: List<MutableList<Char>>
    private lateinit var instructions: List<String>

    private fun resetYards() {
        var isInInstructions = false
        val tempYard: MutableList<MutableList<Char>> = mutableListOf()
        val tempInstruction: MutableList<String> = mutableListOf()
        for (line in inputList) {
            if (isInInstructions) {
                tempInstruction.add(line)
            }
            else {
                isInInstructions = tempYard.fillYard(line)
            }
        }
        yard = tempYard
        instructions = tempInstruction
    }

    private val single: (Int, Int, Int) -> Unit = { count, from, to ->
        repeat(count) {
            val crate = yard[from-1].removeLast()
            yard[to-1].add(crate)
        }
    }

    private val multi: (Int, Int, Int) -> Unit = { count, from, to ->
        val crateStack = yard[from-1].takeLast(count)
        repeat(count) {yard[from-1].removeLast()}
        yard[to-1].addAll(crateStack)
    }

    private fun String.parse(myFun: (Int, Int, Int) -> Unit){
        val split = this.split(" ")
        myFun(split[1].toInt(), split[3].toInt(), split[5].toInt())
    }

    private fun MutableList<MutableList<Char>>.fillYard(s: String): Boolean {
        if (s.isEmpty()) return true
        val buckets = s.chunked(4).map{ it[1] }
        if (this.isEmpty()) {
            repeat(buckets.size) { this.add(mutableListOf()) }
        }
        buckets.forEachIndexed{ i, n -> if (n.isLetter()) {this[i].add(0, n)}}
        return false
    }

    private fun showYardTop() {
        for (stack in yard) print(stack.last())
        println()
    }

    private fun showYard() {for (stack in yard) println(stack)}

    override fun a() {
        resetYards()
        for (s in instructions) s.parse(single)
        showYardTop()
    }

    override fun b() {
        resetYards()
        for (s in instructions) s.parse(multi)
        showYardTop()
    }


}