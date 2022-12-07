package dev.brdlf.aoc2022

import java.lang.Exception

const val STORAGE_SPACE = 70_000_000
const val TARGET_SPACE = 30_000_000

class Day7(isTest: Boolean): Day(isTest) {
    override val number: Int
        get() = 7

    private val commands: MutableList<String> = mutableListOf()
    private var currentDirectory: Place = Place("/")
    data class Place(val name: String, val size: Long? = null, val children: Set<String>? = null, val parent: String? = null)
    private val store = mutableMapOf<String, Place>(currentDirectory.name to currentDirectory)

    init {
        val sc = getScanner()
        while (sc.hasNextLine()){
            commands.add(sc.nextLine())
        }
        for (c in commands) {
            if (c.startsWith('$')) command(c)
            else notCommand(c)
        }
    }

    private fun command(s: String) {
        val foo = s.split(" ")
        when (foo[1]) {
            "cd" -> changeDirectory(foo[2])
            "ls" -> return
            else -> throw Exception("Shouldn't be in command right now")
        }
    }
    private fun changeDirectory(s: String){
        currentDirectory = when (s) {
            ".." -> store[currentDirectory.parent] ?: throw Exception("tried going to parent of $currentDirectory but has no parent")
            "/" -> store["/"]!!
            else -> store["${currentDirectory.name}/$s"] ?: throw Exception("Tried to access store but could not find $s")
        }
    }
    private fun notCommand(s: String) {
        val (size, name) = s.split(" ")

        val newPlace = Place(name = "${currentDirectory.name}/$name", size = size.toLongOrNull(), parent = currentDirectory.name)

        store["${currentDirectory.name}/$name"] = newPlace

        val kids = currentDirectory.children?: emptySet()
        currentDirectory = currentDirectory.copy(children = kids.plus("${currentDirectory.name}/$name"))

        store[currentDirectory.name] = currentDirectory
    }

    private fun getSize(s: String): Long {
        val current = store[s]?: throw Exception("I don't exist: $s")
        var size = current.size?: current.children?.fold(0L){ acc, child -> acc + getSize(child)}!!
        store[s] = current.copy(size = size)
        return size
    }

    private fun partOne(){
        println(store.filterValues { it.size == null }.map { (key, _) -> getSize(key) }.filter { it <= 100000 }.sum())
    }
    private fun partTwo() {
        val freeSpace = STORAGE_SPACE - getSize("/")
        val spaceNeeded = TARGET_SPACE - freeSpace
        val eligible = store.filterValues { it.children != null && getSize(it.name) > spaceNeeded }.map { (k, v) -> k to v.size!! }
        println(eligible.minByOrNull { it.second }!!)
    }

    override fun a() {
        partOne()
    }

    override fun b() {
        partTwo()
    }
}