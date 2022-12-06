package dev.brdlf.aoc2022

class Day5(isTest: Boolean): Day(isTest) {
    override val number: Int
        get() = 5

    private val yard: MutableList<MutableList<Char>> = mutableListOf()
    private val instructions: MutableList<String> = mutableListOf()

    init {
        val sc = getScanner()
        var isInInstructions = false
        while (sc.hasNextLine()){
            if (isInInstructions) {
                instructions.add(sc.nextLine())
            }
            else {
                isInInstructions = fillYard(sc.nextLine())
            }
        }
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

    private fun fillYard(s: String): Boolean {
        if (s.isEmpty()) return true
        val buckets = s.chunked(4).map{ it[1] }
        if (yard.isEmpty()) {
            repeat(buckets.size) { yard.add(mutableListOf()) }
        }
        buckets.forEachIndexed{ i, n -> if (n.isLetter()) {yard[i].add(0, n)}}
        return false
    }

    private fun showYardTop() { for (stack in yard) print(stack.last()) }

    private fun showYard() {for (stack in yard) println(stack)}

    override fun a() {
        for (s in instructions) s.parse(single)
        showYardTop()
    }

    override fun b() {
        for (s in instructions) s.parse(multi)
        showYardTop()
    }


}