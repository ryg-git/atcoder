fun next() = readLine()!!.trim()

@kotlin.ExperimentalStdlibApi
fun main() {
    val (n, m) = next().split(' ').map { it.toInt() }

    val bPawns = Array(m) {
        val (x, y) = next().split(' ').map { it.toInt() }
        x to y
    }.groupBy { it.first }.entries.sortedBy {it.key}

    val ans = hashSetOf(n)

    for ((_, pos) in bPawns) {

        val diagonal = pos.filter { it.second + 1 in ans || it.second - 1 in ans  }

        pos.forEach { ans.remove(it.second) }

        diagonal.forEach { ans.add(it.second) }
    }

    println(ans.size)
}
