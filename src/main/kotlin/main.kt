fun next() = readLine()!!.trim()

@kotlin.ExperimentalStdlibApi
fun main() {
    val n = next().toInt()

    val edges = List(n - 1) {
        val (u, v, w) = next().split(" ")
        Triple(u.toInt() - 1, v.toInt() - 1, w.toLong())
    }

    val nodes = List(n){mutableListOf<Pair<Int, Long>>()}

    edges.forEach { (u, v, w) ->
        nodes[u].add(v to w)
        nodes[v].add(u to w)
    }

    val fromRoot = Array(n) {-1L}.also { it[edges.first().first] = 0L }

    val q = ArrayDeque<Int>()

    q.addFirst(edges.first().first)

    while (!q.isEmpty()) {
        val u = q.removeLast()
        for ((v, w) in nodes[u]) {
            if(fromRoot[v] >= 0) continue
            fromRoot[v] = fromRoot[u] xor w
            q.addFirst(v)
        }
    }

    val cnt = MutableList(60) {0} to MutableList(60) {0}

    val mod = 1000_000_007

    var ans = 0L

    for (i in 0 until 60) {
        for (d in fromRoot) {
            if(d shr i and 1L == 0L) cnt.first[i]++
            else cnt.second[i]++
        }
        ans += (cnt.first[i]*((1L shl i) % mod))%mod * cnt.second[i]
        ans %= mod
    }

    ans.let(::println)
}
