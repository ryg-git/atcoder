fun next() = readLine()!!

fun main() {
    val (h, w) = readLine()!!.split(" ").map(String::toInt)
    val board = mutableListOf<List<Int>>()
    repeat(h) {
        val row = readLine()!!.trim().toCharArray().map { c -> if(c == '+') 1 else -1 }.toList()
        board.add(row)
    }
    val dp = Array(h) {IntArray(w) {0}}
    val getTurn = { r: Int, c: Int -> (r + c) and 1 == 0 }

    for (i in h - 1 downTo 0)
        for (j in w - 1 downTo 0) {
            if (i == h - 1 && j == w - 1) continue
            if (getTurn(i, j)) {
                dp[i][j] = Int.MIN_VALUE
                if(j < w - 1) dp[i][j] = maxOf(dp[i][j], dp[i][j + 1] + board[i][j + 1])
                if(i < h - 1) dp[i][j] = maxOf(dp[i][j], dp[i + 1][j] + board[i + 1][j])
            } else {
                dp[i][j] = Int.MAX_VALUE
                if(j < w - 1) dp[i][j] = minOf(dp[i][j], dp[i][j + 1] - board[i][j + 1])
                if(i < h - 1) dp[i][j] = minOf(dp[i][j], dp[i + 1][j] - board[i + 1][j])
            }
        }

    val ans = dp[0][0]

    when {
        ans > 0 -> "Takahashi"
        ans < 0 -> "Aoki"
        else -> "Draw"
    }.let(::println)
}