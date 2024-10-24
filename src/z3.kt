
//Имеется массив из символов русского алфавита (все 33 символа, могут быть не по порядку). Символы алфавита нумеруются от 1 до 33.
// Каждое число используется только один раз.  Сообщение шифруется с помощью ключевого слова, задаваемого пользователем. Номер символа ключевого слова показывает, на сколько нужно сдвинуться по массиву из символов русского алфавита.
// Составить программу шифровки и дешифровки строкового выражения (то есть программа спрашивает - зашифровать или расшифровать текст и ключевое слово). Первый массив считать закольцованным. Регистр букв не имеет значения.

//А   Б   В  Г   Д   Е  Ё   Ж   З   И   Й  К   Л  М   Н  О   П  Р   С   Т   У   Ф  Х   Ц   Ч  Ш   Щ   Ь   Ы   Ъ    Э  Ю  Я
//21  13  4  20  22  1  25  12  24  14  2  28  9  23  3  29  6  16  15  11  26  5  30  27  8  18  10  33  31  32  19  7  17
//Ключевое слово - ПОЛЕ
//Исходный текст - СООБЩЕНИЕ
//Зашифрованный текст - АЁФИРХЖСЮ



val alphabet = mapOf('А' to 21, 'Б' to 13, 'В' to 4, 'Г' to 20, 'Д' to 22, 'Е' to 1, 'Ё' to 25, 'Ж' to 12, 'З' to 24,
    'И' to 14, 'Й' to 2, 'К' to 28, 'Л' to 9, 'М' to 23, 'Н' to 3, 'О' to 29, 'П' to 6, 'Р' to 16,
    'С' to 15, 'Т' to 11, 'У' to 26, 'Ф' to 5, 'Х' to 30, 'Ц' to 27, 'Ч' to 8, 'Ш' to 18, 'Щ' to 10,
    'Ь' to 33, 'Ы' to 31, 'Ъ' to 32, 'Э' to 19, 'Ю' to 7, 'Я' to 17)

//keys - то, что в скобках
//values - числовое значение


fun main() {

    println("Выберите вариант:" +
            "\n1.Зашифровать текст" +
            "\n2.Расшифровать текст")
    val action = readln().toIntOrNull()

    if (action != 1 && action != 2 && action == null) {
        println("Неверный ввод.")
        return
    }

    println("Введите ключевое слово:")
    val keyword = readln().uppercase()
    if (keyword.isEmpty()) {
        println("Неверный ввод.")
        return}
    // проверка, состоит ли ключевое слово только из русских букв
    if (!isValidKeyword(keyword)) {
        println("Ключевое слово должно содержать только русские буквы.")
        return
    }

    println("Введите текст:")
    val text = readln().uppercase()
    if (text.isEmpty()) {
        println("Неверный ввод.")
        return}
    // проверка, состоит ли текст только из русских букв
    if (!isValidKeyword(text)) {
        println("Текст должен содержать только русские буквы.")
        return
    }



    val result =
        if (action == 1) { encrypt(text, keyword, alphabet) }
        else if (action == 2) { decrypt(text, keyword, alphabet)}
        else {
            "Неверный ввод."
            return
        }


    println("Результат: $result")
}

fun isValidKeyword(text: String): Boolean {
    for (char in text) {
        if (!alphabet.containsKey(char)) {
            return false
        }
    }
    return true
}



fun encrypt(text: String, keyword: String, alphabet: Map<Char, Int>): String {
    var resultText = "" //зашифрованный текст
    var keywordIndex = 0 //индекс символа в ключевом слове
    for (char in text) {    // перебиираем символы текста
        val letterNum = alphabet[char]!! // получаем НОМЕР данной буквы в исходном слове
        val shift = alphabet[keyword[keywordIndex % keyword.length]]!! // получаем НОМЕР данной буквы в ключевом слове |||| keywordIndex % keyword.length - чтобы индекс буквы в ключевом слове не выходил за границы
        var resultNum = letterNum + shift
        if (resultNum > 33) { resultNum = resultNum % 33}
        val letterResult = alphabet.filterValues { it == resultNum}.keys.first() //находим букву по новому номеру(шифруем)
        resultText += letterResult
        keywordIndex++
    }
    return resultText
}

fun decrypt(text: String, keyword: String, alphabet: Map<Char, Int>): String {
    var resultText = "" //расшифрованный текст
    var keywordIndex = 0 //индекс символа в ключевом слове
    for (char in text) {
        val letterNum = alphabet[char]!! // получаем НОМЕР данной буквы в исходном слове // два восклицательных знака используются как оператор "не NULL".
        val shift = alphabet[keyword[keywordIndex % keyword.length]]!! // получаем НОМЕР данной буквы в ключевом слове |||| keywordIndex % keyword.length - чтобы индекс буквы в ключевом слове не выходил за границы
        var resultNum = letterNum - shift
        if (resultNum < 1) { resultNum = 33 + resultNum}
        val letterResult = alphabet.filterValues { it == resultNum}.keys.first() //находим букву по новому номеру(шифруем)
        resultText += letterResult
        keywordIndex++
    }
    return resultText
}