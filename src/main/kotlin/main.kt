fun next() = readLine()!!.trim()

@kotlin.ExperimentalStdlibApi
fun main() {
    solve()
}

fun solve() {
    val (n, q) = next().split(' ').map(String::toInt)
    val numbs = next().split(' ').map(String::toLong)

    val k = LongArray(q) {
        next().toLong()
    }

    for (query in k) {
        var l = 0
        var r = n

        while (r > l) {
            val mid = (r + l) / 2

            val out = numbs[mid] - mid - 1

            if (query <= out) r = mid
            else l = mid + 1
        }

        println(l + query)
    }
}