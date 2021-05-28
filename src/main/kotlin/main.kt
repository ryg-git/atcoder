import kotlin.math.min

fun next() = readLine()!!.trim()

@kotlin.ExperimentalStdlibApi
fun main() {
    val n = readLine()!!.trim().toInt()
    val permutation = readLine()!!.trim().split(' ').map { it.toInt() - 1 }
    val costs = List(n) {
        val (a, b, c) = readLine()!!.trim().split(' ').map(String::toInt)
        Triple(a, minOf(a, b), minOf(a, c))
    }

    val minArr = IntArray(n + 1) { Int.MAX_VALUE }.also { it[0] = 0 }
    val dp = IntArray(n + 1) { Int.MAX_VALUE }.also { it[0] = 0 }

    val aSum = costs.fold(mutableListOf(0)) { acc, ele ->
        acc.add(acc.last() + ele.first)
        acc
    }
    val bSum = costs.fold(mutableListOf(0)) { acc, ele ->
        acc.add(acc.last() + ele.second)
        acc
    }
    val cSum = costs.fold(mutableListOf(0)) { acc, ele ->
        acc.add(acc.last() + ele.third)
        acc
    }

    val getMin = { ind: Int ->
        var a = Int.MAX_VALUE
        for (i in 0..ind) a = min(a, minArr[i])
        a
    }

    var ans = Int.MAX_VALUE

    for (i in 0 until n) {
        dp[i + 1] = minOf(bSum[i], getMin(permutation.indexOf(i)) + aSum[i])
        ans = min(ans, dp[i + 1] + cSum[n] - cSum[i + 1])
        minArr[permutation.indexOf(i)] = dp[i + 1] - aSum[i + 1]
    }

    ans.let(::println)
}
