package dev.brdlf.aoc2022

class Day11(isTest: Boolean): Day(isTest) {
    override val number: Int
        get() = 11

    var divisorCommonDenominator: Long

    class Monkey(private val items: MutableList<Long>, private val operation: (Long) -> Long, val divisor: Long, private val ifTrue: Int, private val ifFalse: Int){
        val id = indexing++
        var inspectedCount = 0

        companion object{
            var indexing = 0
            val monkeyList = mutableListOf<Monkey>()
        }

        private fun throwTo(target: Int, item: Long) {
            monkeyList[target].items.add(item)
            items.removeFirst()
        }

        private fun isDivisibleThrow(item: Long) {
            if (item % divisor == 0L) {
                throwTo(ifTrue, item)
            }
            else {
                throwTo(ifFalse, item)
            }
        }

        fun enactRound(PCD: Long? = null) {
            while(items.isNotEmpty()) {
                inspectedCount++
                val n = operation(items.first())
                if (PCD == null) {
                    isDivisibleThrow(n/3)
                } else isDivisibleThrow(n%PCD)
            }
        }
    }

    init {
        val monkeyList = inputList.chunked(7).map{ makeAMonkey(it) }
        Monkey.monkeyList.addAll(monkeyList)
        divisorCommonDenominator = monkeyList.map { it.divisor }.fold(1L) { acc, it -> it * acc}
    }

    private fun makeAMonkey(info: List<String>): Monkey {
        val startingItems = info[1].split(":").last().split(",")
            .map{ it.trim().toLongOrNull()?: throw Exception("Error StartingItems") }.toMutableList()
        val operation = info[2].split(":").last().split("=").last().split(" ").takeLast(2).map { it.trim() }
        val divisor = info[3].split(" ").last().toLongOrNull()?: throw Exception("Error divisor")
        val truecase = info[4].split(" ").last().toIntOrNull()?: throw Exception("Can't determine trueMonkey in MakeAMonkey")
        val falsecase = info[5].split(" ").last().toIntOrNull()?: throw Exception("Can't determine falseMonkey in MakeAMonkey")
        return Monkey(startingItems, stringToLambda(operation), divisor, truecase, falsecase)
    }

    private fun stringToLambda(str: List<String>): (Long) -> Long {
        val m = if (str.last() == "old") null else str.last().toLong()
        return when (str.first()) {
            "*" -> {n -> (n).times((m?: n)) }
            "+" -> {n -> (n).plus((m?: n)) }
            else -> throw Exception("Can't deduce operation")
        }
    }

    private fun logic(rounds: Int, PCD: Long? = null) {
        for (x in 0 until rounds) {
            for (monkey in Monkey.monkeyList) {
                monkey.enactRound(PCD)
            }
        }
        for (monkey in Monkey.monkeyList) {
            println("Monkey ${monkey.id} inspected items ${monkey.inspectedCount} times")
        }
        print(Monkey.monkeyList.map {it.inspectedCount}.sorted().reversed().take(2).fold(1L){ it, acc -> it * acc})
    }

    override fun a() {
        println()
        logic(20)
    }

    override fun b() {
        println()
        logic(10000, divisorCommonDenominator)
    }
}

//Inspect
//Divide 3 round down
//Test

