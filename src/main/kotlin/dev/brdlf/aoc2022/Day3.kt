package dev.brdlf.aoc2022

import java.lang.Exception

class Day3(isTest: Boolean): Day(isTest){
    override val number: Int
        get ()  = 3

    private val pMap: Map<Char, Int> = setPMap()

    private fun setPMap(): Map<Char, Int>{
        var n = 1
        val tempMap: MutableMap<Char, Int> = mutableMapOf()
        for (c in 'a'..'z') tempMap[c] = n++
        for (c in 'A'..'Z') tempMap[c] = n++
//        pMap.forEach { (it, index) -> println("$it: $index")}
        return tempMap
    }

    private fun findShared(first: String, second:String): Char {
        for (n in first) {
            if (second.contains(n, ignoreCase = false)) return n
        }
        throw Exception("No char found for findShared")
    }

    private fun findBadge(first: String, second:String, third: String): Char {
        for (n in first){
            if (second.contains(n, ignoreCase = false)) {
                if (third.contains(n, ignoreCase = false)) return n
            }
        }
        throw Exception("No char found for findBadge")
    }

    override fun a() {
        val list = mutableListOf<Char>()
        for (sack in inputList){
            val firstCompartment = sack.take(sack.length/2)
            val secondCompartment = sack.drop(sack.length/2)
            list.add(findShared(firstCompartment, secondCompartment))
        }
        val sum = list.fold(0){ R, C -> R + (pMap[C]?:0)}
        println(sum)
    }

    override fun b() {
        val list = mutableListOf<Int>()
        val sackIterator = inputList.listIterator()
        while (sackIterator.hasNext()){
            val first = sackIterator.next()
            val second = sackIterator.next()
            val third = sackIterator.next()
            val badge = pMap[findBadge(first, second, third)]?:0
            list.add(badge)
        }
        println(list.sum())
    }

}