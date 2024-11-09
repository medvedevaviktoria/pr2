
//Задача 5
//Создать приложение, в котором пользователь вводит массив из различных слов.
// На выходе приложение должно показать слова сгруппированные по признаку "состоят из одинаковых букв".
// Например, на входе ["eat", "tea", "tan", "ate", "nat", "bat"]. Получаем группы:
//"ate", "eat", "tea"
//"nat", "tan"
//"bat"



fun main() {

    println("Введите массив слов:")
    val input = readln().split(" ")


    //ключ - набор букв
    //значение - список слов
    val result = mutableMapOf<String, MutableSet<String>>()//карта <строка из букв в словах, список слов из этих одинаковых букв> // string - набор букв; Сет - список слов, состоящих из тих букв.

    for (word in input) {
        val letters = word.toSet().distinct().sorted().toString() //список букв в слове
        val lettersKey = result[letters] // list равен ключу в карте result по значению строки из букв //находим есть ли значения в result равные данному набору букв
        if (lettersKey == null ) {
            result[letters] = mutableSetOf(word) // присваиваем к списку именно этих букв ключ со значением слова, состощего из этих букв
        }
        else {
            lettersKey.add(word) //добавляем слово в значение списка слов к этому ряду букв
        }
    }

    for (group in result.values) { //для каждой группы в значениях карты
        for (word in group) { //для каждого слова в группе
            print("$word \t")
        }
        println()
    }



}
