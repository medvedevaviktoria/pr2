fun main() {
    println("Данная программа находит количество цифр в двумерном массиве.")

    print("Введите количество строк двумерного массива: ")
    val n = readln().toInt()
    print("Введите количество столбцов двумерного массива: ")
    val m = readln().toInt()

    val ArrOne: Array<Int> = Array(n) {(100..999).random()}
    val ArrTwo: Array<Array<Int>> = Array(n) {
        Array(m) { (100..999).random()}
    }
    for (i in 0..n-1)
    {
        for (j in 0 .. m-1)
        {
            print(ArrTwo[i][j].toString() + "\t\t")
        }
        println()
    }
    var count = mutableSetOf<Char>()
    for (i in 0..n-1){
        for (j in 0 .. m-1){
            val num = ArrTwo[i][j].toString()
            for (char in num)
            {count.add(char)}
        }
    }
    print("Количество разных цифр равно: ${count.size}")
}