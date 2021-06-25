@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import kotlin.math.max
import java.util.*

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> = TODO()

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean = TODO()

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit = TODO()

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = TODO()

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> = TODO()

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> = TODO()

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String = TODO()

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean = TODO()

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> = TODO()

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean = TODO()

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> = TODO()

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> = TODO()

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
//fun result(
//    prise: Map<Pair<String, Int>, Pair<Double, Int>>,
//    ppg: Map<Pair<String, Int>, Pair<Double, Int>>,
//    capacity: Int
//): Set<String> {
//    var sumP = 0 // сумма стоимости по цене
//    var sumPPG = 0 // сумма стоимости по цене за грамм
//    var sumWP = 0 // сумма весов по цене
//    var sumWPPG = 0 // сумма весов по цене за грамм
//    val setPrise = mutableSetOf<String>() //результат1
//    val setPPG = mutableSetOf<String>() //результат2
//
//    var startTime = Calendar.getInstance().timeInMillis
//    for (i in prise.keys) {
//
//        val weight = prise.getValue(i).second //вес текущего скровища
//        val price = i.second // стоимость текущего сокровища
//
//        if (weight <= capacity && sumWP < capacity) { //не привышает ли вес общую вместимость, сумма весов меньше вместимости
//            sumWP += weight //добавляем вес к общей сумме
//            sumP += price  //добавляем цену к общей сумме
//            setPrise += i.first //добавляем название в множество
//
//            if (sumWP > capacity) { //если привышен лимит
//                sumWP -= weight          //удалить последнее добавленное
//                sumP -= price           //удалить последнее добавленное
//                setPrise -= i.first    //удалить последнее добавленное
//            }
//        }
//    }
//    var stopTime = Calendar.getInstance().timeInMillis
//    for (i in ppg.keys) {
//
//        val weight = ppg.getValue(i).second //вес текущего скровища
//        val price = i.second //стоимость текущего сокровища
//
//
//        if (weight <= capacity && sumWPPG < capacity) { //не привышает ли вес общую вместимость, сумма весов меньше вместимости
//            sumWPPG += weight //добавляем вес к общей сумме
//            sumPPG += price  //добавляем цену к общей сумме
//            setPPG += i.first //добавляем название в множество
//
//            if (sumWPPG > capacity) { //если привышен лимит
//                sumWPPG -= weight          //удалить последнее добавленное
//                sumPPG -= price           //удалить последнее добавленное
//                setPPG -= i.first        //удалить последнее добавленное
//
//            }
//        }
//    }
//
//
//    println("Prise: $sumP, PPG: $sumPPG")
//    return if (sumP >= sumPPG) { //сумма какой сортировки больше
//        setPrise
//    } else {
//        setPPG
//    }
//}


fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val weights = mutableListOf<Int>()
    val values = mutableListOf<Int>()

    for (i in treasures.keys) {
        weights.add(treasures.getValue(i).first)
        values.add(treasures.getValue(i).second)
    }

    val n = treasures.size

    val lookup = Array(n + 1) { i -> Array(capacity + 1) { j -> 0 } }

    for (i in 0..n) {
        for (j in 0..capacity) {
            if (i == 0 || j == 0) {
                lookup[i][j] = 0
            } else if (weights[i - 1] <= j) {
                lookup[i][j] = max(values[i - 1] + lookup[i - 1][j - weights[i - 1]], lookup[i - 1][j])
            } else {
                lookup[i][j] = lookup[i - 1][j]
            }
        }
    }
    println(lookup[n][capacity])
    return setOf()
}
//    val prisePerGram = mutableMapOf<Pair<String, Int>, Pair<Double, Int>>()
//
//
////создаем новую map (Название сокровища, цена сокровища) to (цена за ед.веса, вес)
//    for (item in treasures.keys) {
//        val ppg = treasures.getValue(item).second / treasures.getValue(item).first.toDouble()
//        prisePerGram[Pair(item, treasures.getValue(item).second)] = Pair(ppg, treasures.getValue(item).first)
//    }
//
//
////сортируем карту от большей цены за ед.веса к меньшей
//
//    val sortPPG = prisePerGram.toList().sortedByDescending { it.second.first }.toMap()
//    println(sortPPG)
//
////сортируем карту от большей цены к меньшей
//    val sortPrice = prisePerGram.toList().sortedByDescending { it.first.second }.toMap()
//    println(sortPrice)
//
//
//
//    return result(sortPrice, sortPPG, capacity)

