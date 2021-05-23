fun next() = readLine()!!
fun nextInt() = next().toInt()

fun main() {
    val (h, w) = readLine()!!.split(" ").map(String::toInt)
    val board = mutableListOf<List<Char>>()
    repeat(h) {
        val row = readLine()!!.toCharArray().toList()
        board.add(row)
    }
}