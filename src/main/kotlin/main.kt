import java.util.*
import kotlin.math.min

fun next() = readLine()!!.trim()

@kotlin.ExperimentalStdlibApi
fun main() {
    solve()
}

data class Edge(val from: Int, val to: Int, val cap: Int)
data class EData(val to: Int, var cap: Int, val rev: Int)

fun maxFlow(n: Int, E: List<Edge>, s: Int, t: Int): Int {

    val parents = IntArray(n) { -1 }
    val levels = IntArray(n) { -1 }
    val depth = IntArray(n) { -1 }

    val paths = Array(n) {mutableListOf<EData>()}

    for (i in E) {
        paths[i.from].add(EData(i.to, i.cap, paths[i.to].size))
        paths[i.to].add(EData(i.from, 0, paths[i.from].size - 1))
    }


    fun bfs(s: Int, t: Int): Boolean {
        var head = 0

        var tail = head

        depth[s] = 0
        levels[head++] = s

        while (head > tail) {
            val pt = levels[tail++]

            if (pt == t) return true

            for (i in paths[pt]) {
                if (depth[i.to] == -1 && i.cap > 0) {
                    depth[i.to] = depth[pt] + 1
                    levels[head++] = i.to
                }
            }
        }

        return false
    }

    fun dfs(u: Int, f: Int): Int {
        if(u == t) return f
        for (i in paths[u]) {
            if (i.cap > 0 && depth[u] < depth[i.to]) {
                val gain = dfs(i.to, min(f, i.cap))
                if (gain > 0) {
                    i.cap -= gain
                    paths[i.to][i.rev].cap += gain
                    return gain
                }
            }
        }
        return 0
    }

    var maxFlowAns = 0

    while (bfs(s, t)) {
        var pathFlows = Int.MAX_VALUE

        while (true) {
            pathFlows = dfs(s, pathFlows)
            if (pathFlows <= 0) break
            maxFlowAns += pathFlows
        }

        maxFlowAns += pathFlows
        Arrays.fill(parents, -1)
        Arrays.fill(depth, -1)
    }

    return maxFlowAns
}


fun solve() {
    val (h, w, n) = next().split(' ').map { it.toInt() }

    val matrix = List(n) {
        next().split(' ').map { it.toInt() - 1}
    }

    val s = h + w + 2*n
    val t = s + 1

    val edges = mutableListOf<Edge>()

    fun nodeP(i: Int) = h + w + i

    fun nodeP2(i: Int) = h + w + n + i

    for (i in 0 until h) edges.add(Edge(s, i, 1))

    for (i in 0 until w) edges.add(Edge(h + i, t, 1))

    matrix.forEachIndexed { index, list ->
        edges.add(Edge(nodeP(index), nodeP2(index), 1))

        val (a, b, c, d) = list

        for (i in a..c) edges.add(Edge(i, nodeP(index), 1))

        for (i in b..d) edges.add(Edge(nodeP2(index), h + i, 1))
    }

    val ans = maxFlow(t + 1, edges, s, t)

    println(ans)
}
