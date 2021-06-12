fun next() = readLine()!!.trim()

@kotlin.ExperimentalStdlibApi
fun main() {
    val (n, k) = next().split(' ').map(String::toInt)

    val heights = next().split(' ').map(String::toInt).sorted().toIntArray()

    val dp = Array(n + 1) { IntArray(31) { 1_000_000_001 } }.also {
        it.mapIndexed { index, i -> i[0] = index }
    }

    for (i in 1..n) {
        val a = heights.getMaxHalf(heights[i - 1])
        for (j in 1..30) {
            val pu = dp[i - 1][j] + 1
            val np = if (a < 0) 0 else dp[a][j - 1]
            dp[i][j] = minOf(pu, np)
        }
    }

    val ans = dp[n].indexOfFirst { it <= k }

    print("$ans  ${if (ans == -1) dp[n][0] else dp[n][ans]}")

}

fun IntArray.getMaxHalf(v: Int): Int {
    var r = size
    var l = 0

    val half = v shr 1

    while (r > l) {
        val mid = (r + l) / 2

        if (get(mid) <= half) l = mid + 1
        else r = mid
    }
    return r
}