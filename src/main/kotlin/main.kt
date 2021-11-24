import java.util.*

fun next() = readLine()!!.trim()

fun main() {
    val (n, m) = next().split(' ').map(String::toInt)
    val cons = List(m) {
        next().split(' ').map {it.toInt() - 1}
    }

    val inn = List(n) { mutableListOf<Int>() }

    val used = IntArray(n)

    for (i in cons) {
        inn[i[0]].add(i[1])
        used[i[1]] += 1
    }

    val ans = mutableListOf<Int>()

    val queue = PriorityQueue<Int>().also { it.addAll(used.indices.filter { i -> used[i] == 0 }) }

    while (queue.isNotEmpty()) {
        val top = queue.poll()
        ans.add(top + 1)
        for (next in inn[top]) {
            if (--used[next] == 0) {
                queue.add(next)
            }
        }
    }

    if(ans.size < n) println(-1)
    else println(ans.joinToString(" "))
}
