fun next() = readLine()!!.trim()

@kotlin.ExperimentalStdlibApi
fun main() {
    val (n, k) = next().split(' ').map { it.toInt() }


    val sq = Array(n) { next().split(' ').map { it.toInt() } }


    var r = 1_000_000_000
    var l = -1

    outer@ while (r - l > 1) {

        val m: Int = (r + l) / 2

        val c = sq.map { arr ->
            arr.map {
                when {
                    it == m -> 0
                    it > m -> 1
                    else -> -1
                }
            }
        }

        val cs = Array(n + 1) { IntArray(n + 1) }
        val csz = Array(n + 1) { IntArray(n + 1) }

        for (i in c.indices) for (j in c[i].indices) {
            cs[i + 1][j + 1] = cs[i + 1][j] + c[i][j]
            csz[i + 1][j + 1] = csz[i + 1][j]

            if (c[i][j] == 0) csz[i + 1][j + 1] += 1
        }

        for (i in 1..n) for (j in c.indices) {
            cs[j + 1][i] += cs[j][i]
            csz[j + 1][i] += csz[j][i]
        }

        for (i in 0..n - k) for (j in 0..n - k) {
            val s = cs[i + k][j + k] + cs[i][j] - cs[i + k][j] - cs[i][j + k]
            val sz = csz[i + k][j + k] + csz[i][j] - csz[i + k][j] - csz[i][j + k]

            if (s <= sz) {
                r = m
                continue@outer
            }
        }
        l = m
    }

    println(r)
}
