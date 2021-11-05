fun next() = readLine()!!.trim()

@kotlin.ExperimentalStdlibApi
fun main() {
    solve()
}

fun solve() {
    val n = next().toInt()
    val fuses  = List(n) {next().split(' ').map { it.toDouble()}}

    var total = 0.0

    for (i in fuses) {
        total += i[0] / i[1]
    }

    var tm = total / 2

    var dist = 0.0

    for (i in fuses) {
        val t = i[0] / i[1]

        if (t < tm) {
            tm -= t
            dist += i[0]
        } else {
            dist += tm * i[1]
            break
        }
    }

    println(dist)
}
