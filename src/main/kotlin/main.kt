fun next() = readLine()!!.trim()

@kotlin.ExperimentalStdlibApi
fun main() {
    solve()
}

const val MOD = 1_000_000_007

fun solve() {
    val (n, m, k) = next().split(' ').map(String::toInt)
    if (n > m + k) {
        println(0)
        return
    }

    if (n == 0) {
        println(1)
        return
    } else if (m == 0) {
        if (n <= k) println(1) else println(0)
        return
    }

    val fact = LongArray (n + m + 1) {1}
    val inv = LongArray (n + m + 1) {1}

    for (i in 2..fact.lastIndex) {
        fact[i] = i * fact[i - 1] % MOD
    }

    inv[inv.lastIndex] = power(fact.last())

    for (i in inv.lastIndex downTo 2) {
        inv[i - 1] = i * inv [i] % MOD
    }

    val choose = {nn: Int, r: Int -> if (r <= nn) fact[nn] * inv[r] % MOD * inv[nn - r] % MOD else 0L}

    println((choose(n + m, m) - choose(n + m, m + k + 1) + MOD) % MOD)

}

private fun power(base: Long): Long {
    var r = 1L
    var b = base
    var exp = MOD - 2
    while (exp > 0) {
        if((exp and 1) == 1) {
            r = r * b % MOD
        }
        b = b * b % MOD
        exp = exp shr 1
    }

    return r
}