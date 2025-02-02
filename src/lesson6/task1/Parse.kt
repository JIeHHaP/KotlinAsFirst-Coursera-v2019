@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import java.lang.IllegalArgumentException

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val month = mapOf(
        "января" to "01",
        "февраля" to "02",
        "марта" to "03",
        "апреля" to "04",
        "мая" to "05",
        "июня" to "06",
        "июля" to "07",
        "августа" to "08",
        "сентября" to "09",
        "октября" to "10",
        "ноября" to "11",
        "декабря" to "12"
    )
    val datePars = str.split(" ").toMutableList()

    when {
        datePars.size < 3 -> return ""
        month[datePars[1]] == null -> return ""
        datePars[0].toInt() !in 1..31 || datePars[2].toInt() < 0 -> return ""
        datePars[0].toInt() > 28 && !daysInMonth(datePars[2].toInt()) && datePars[1] == "февраля" -> return ""
        datePars[0].toInt() > 29 && daysInMonth(datePars[2].toInt()) && datePars[1] == "февраля" -> return ""
        (month.getValue(datePars[1]).toInt() in 4..6 step 2 || month.getValue(datePars[1])
            .toInt() == 11 || month.getValue(datePars[1])
            .toInt() == 9) && datePars[0].toInt() > 30 -> return ""
    }

    datePars[1] = month.getValue(datePars[1])

    val d = datePars[0].toInt()
    val m = datePars[1].toInt()
    val y = datePars[2].toInt()

    return String.format("%02d.%02d.%d", d, m, y)
}

fun daysInMonth(year: Int): Boolean {
    return when {
        year % 400 == 0 -> {
            true
        }
        year % 100 == 0 -> {
            false
        }
        year % 4 == 0 -> {
            true
        }
        else -> {
            false
        }
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val month = mapOf(
        "01" to "января",
        "02" to "февраля",
        "03" to "марта",
        "04" to "апреля",
        "05" to "мая",
        "06" to "июня",
        "07" to "июля",
        "08" to "августа",
        "09" to "сентября",
        "10" to "октября",
        "11" to "ноября",
        "12" to "декабря"
    )
    val datePars = digital.split(".").toMutableList()

    when {
        datePars.size != 3 -> return ""
        month[datePars[1]] == null -> return ""
        datePars[0].toInt() !in 1..31 || datePars[2].toInt() < 0 -> return ""
        datePars[0].toInt() > 28 && !daysInMonth(datePars[2].toInt()) && datePars[1] == "02" -> return ""
        datePars[0].toInt() > 29 && daysInMonth(datePars[2].toInt()) && datePars[1] == "02" -> return ""
        (datePars[1].toInt() in 4..6 step 2 || datePars[1]
            .toInt() == 11 || datePars[1]
            .toInt() == 9) && datePars[0].toInt() > 30 -> return ""
    }

    datePars[1] = month.getValue(datePars[1])

    val d = datePars[0].toInt()
    val m = datePars[1]
    val y = datePars[2]

    return String.format("%d %s %s", d, m, y)
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {

    val regStop = Regex("[^-\\d()+\\s]").find(phone)
    val regStop2 = Regex("""\(\)""").containsMatchIn(phone)
    val reg = Regex("(^\\+\\d+|\\d+)").findAll(phone)

    val result = StringBuilder()
    for (matchedText in reg) {
        result.append(matchedText.value)
    }

    return if (regStop != null || regStop2) {
        ""
    } else result.toString()
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int = TODO()
//{
//    val stop = Regex("[^\\d\\-%\\s]").find(jumps)
//    val reg = Regex("(\\d+)").findAll(jumps).map { it.groupValues[0] }.toList()
//    val intList = mutableListOf<Int>()
//    for (i in reg) {
//        val numb = i.toInt()
//        intList += numb
//    }
//    return if (stop != null) -1 else intList.maxOrNull() ?: -1
//}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int = TODO()
//{
//    val stop = Regex("[^\\d+\\s-%]").find(jumps)
//    return if (stop != null) {
//        -1
//    } else {
//        val reg = Regex("\\d+\\s[+]").findAll(jumps).map { it.groupValues[0] }.toList()
//        val successList = mutableListOf<Int>()
//        for (i in reg.indices) {
//            val success = Regex("\\d+").find(reg[i])
//            successList.add(success!!.value.toInt())
//        }
//        successList.maxOrNull() ?: -1
//    }
//}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val stop = Regex("[^-+\\s\\d]|\\+\\+|-\\+|\\+\\d|-\\d").find(expression)
    val firstInt = Regex("^\\d").find(expression)
    val splitStr = expression.split(" ")
    var result: Int
    if (stop != null || firstInt == null) {
        throw IllegalArgumentException()
    } else {
        result = splitStr[0].toInt()
        for (i in splitStr.indices) {
            when {
                splitStr[i] == "+" -> result += splitStr[i + 1].toInt()
                splitStr[i] == "-" -> result -= splitStr[i + 1].toInt()
            }
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val splitStr = str.toLowerCase().split(" ")
    var result = -1

    for (i in splitStr.indices) {
        if (splitStr.size < 2) {
            return result
        }
        if (i + 1 in splitStr.indices) {
            if (splitStr[i] == splitStr[i + 1]) {
                result += 1
                return result
            } else {
                result += splitStr[i].length + 1
            }
        } else {
            result = -1
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val stop = Regex("[а-яА-ЯёЁ]+\\s\\d+").find(description)

    return ""
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
