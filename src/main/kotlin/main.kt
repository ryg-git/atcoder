import java.util.*

fun next() = readLine()!!.trim()

fun solveH(x: Long, y: Long, a: Long, b: Long, c: Long): Boolean {
    val r = (a + x - 1) / x
    val s = (b + x - 1) / x
    val t = (c + x - 1) / x
    return r + s + t in 0 .. y
}



fun main() {
    val (x, y, a, b, c) = next().split(' ').map(String::toLong)
    val axis = listOf(x, y)
    val area = listOf(a, b, c)

    val ans = (0..1).any {
        true
    }
}
