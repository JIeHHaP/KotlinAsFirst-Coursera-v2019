@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */


fun digitNumber(n: Int): Int {
    var numb = n
    var count = if (numb == 0) 1 else 0
    while (numb != 0) {
        count++
        numb /= 10
    }
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 1
    var fib2 = 1
    var i = 0
    while (i < n - 2) {
        val fibSum = fib1 + fib2
        fib1 = fib2
        fib2 = fibSum
        i += 1
    }
    return fib2
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    fun nod(m: Int, n: Int): Int {
        var fNumb = m
        var sNumb = n
        while (fNumb != sNumb) {
            if (fNumb > sNumb) {
                fNumb -= sNumb
            } else {
                sNumb -= fNumb
            }
        }
        return fNumb
    }
    return abs(m * n) / nod(m, n)
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var divisor = 2
    while (n % divisor != 0) {
        divisor++
    }
    return divisor
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var divisor = n - 1
    while (n % divisor != 0) {
        divisor--
    }
    return divisor
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val k: Boolean

    var divisor = when {
        m <= n -> m
        else -> n
    }
    while (divisor > 0) {
        if (m % divisor != 0 || n % divisor != 0) {
            divisor--
        } else if (m % divisor == 0 && n % divisor == 0 && divisor == 1) {
            k = true
            return k
        } else {
            return false
        }
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k = m
    var a: Double
    while (k in m..n) {
        a = sqrt(k.toDouble())
        if ((a - a.toInt()) == 0.0) return true
        k++
    }
    return false
}


/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var numb = x
    var count = 0
    while (numb != 1) {
        if (numb % 2 == 0) {
            numb /= 2
            count++
        } else {
            numb = 3 * numb + 1
            count++
        }
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var fNumb = n
    var sNumb = 0
    var tNumb: Int
    while (fNumb > 0) {
        tNumb = fNumb % 10
        fNumb /= 10
        sNumb *= 10
        sNumb += tNumb
    }
    return sNumb
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var numb = n
    var revers = 0
    var rem: Int
    while (numb != 0) {
        rem = numb % 10
        revers = revers * 10 + rem
        numb /= 10
    }
    if (n == revers) {
        return true
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val rem = n % 10
    var divisor = 100

    while (n > 10) {
        if ((n % divisor) / (divisor / 10) == rem && n / divisor != 0) {
            divisor *= 10
        } else return (n % divisor) / (divisor / 10) != rem
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun lenOfInt(n: Int): Int {
    println("Запуск 1й функц")
    var number = n
    var res = 1
    while (number >= 10) {
        res++
        number /= 10
    }
    return res
}

fun digFromInt(n: Int, k: Int): Int {
    println("Запуск 2й функции")
    var i = 0
    var numbK = k
    var numbN = n
    if (numbK <= 0 || numbK > lenOfInt(numbN)) {
        println("Error")
    } else {
        numbK = lenOfInt(numbN) - numbK + 1
        while (numbK > 1) {
            numbN /= 10
            numbK--
        }
        i = numbN % 10
        println("ретёрт I - $i")
        return i

    }
    return i

}

fun squareSequenceDigit(n: Int): Int {
//    println("старт основной функции")
//    var curLen = 1
//    var powNumb = 1
//    var iTemp = powNumb * powNumb
//
//    println("Заданное число: $n")
//    if (n <= 0) {
//        println("Введено некорректное число")
//    } else {
//        if (n < 2) {
//            println("меньше 2")
//            return 1
//        } else {
//            while (curLen + lenOfInt(iTemp) < n) {
//                println("запуск цикла squareSequenceDigit")
//                curLen += lenOfInt(iTemp)
//                powNumb++
//                println(powNumb)
//                iTemp = powNumb * powNumb
//                println("iTemp - $iTemp")
//            }
//        }
//    }
//    println("Ответ: ${digFromInt(iTemp, n - curLen)}")
//    return digFromInt(iTemp, n - curLen)
//}
    var numbToPow = 0 // чило возводимое в квадрат
    var quantityInPow = 0 // для подсчета количества цифр в квадрате
    var scan = 0 // поиск нужной цифры
    var divisor = 0 // делитель для проверки количества символов в квадрате
    var pow = 0 // возведение в квадрат
    var result = 0 // результат, цифра последовательности под номером k

    while (scan < n) { // значение сканера меньше заданного числа
        numbToPow += 1 // увеличиваем число возводимое в квадрат
        pow = numbToPow * numbToPow //возводим в квадрат
        quantityInPow = 1 // задаем количество цифр в квадрате
        divisor = 10    // задаем значение делителя
        while (pow >= 10) { // все цифры квадрата учтены?
            quantityInPow += 1 // увеличиваем количество цифр в квадрате
            pow /= 10
        }
        scan += quantityInPow // прибавляем количество цифр в квадрате к сканеру
    }
    scan -= quantityInPow //отнимаем количество цифр в квадрате от сканера
    divisor /= 10 // уменьшаем делитель
    while (scan != n) { //сканер не равен заданному числу
        result = pow / divisor % 10 // результат равен остатку от деления выражения (квадрат числа / делитель)
        divisor /= 10 // уменьшение делителя
        scan += 1 // увеличиваем значение сканера
    }
    return result // возвращаем результат
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {

    fun lenOfInt(n: Int): Int { //узнать длину числа
        var number = n // полученное число Фибоначчи
        var res = 1 // длина числа
        while (number >= 10) { //если число больше 10
            res++   // увеличить длину на 1
            number /= 10 // разделить число Фибоначи на 10
        }
        return res // возвращаем количество чифр в числе Фибоначчи
    }

    fun digFromInt(n: Int, k: Int): Int { // n- число Фибоначчи, k - заданное число - текущая длинна
        var i = 0  // ответ на задание
        var numbK = k // k - заданное число - текущая длинна
        var numbN = n // n- число Фибоначчи
        if (numbK <= 0 || numbK > lenOfInt(numbN)) { //проверка на неверные данные
            println("Error")
        } else {
            numbK = lenOfInt(numbN) - numbK + 1 //длина числа - k +1
            while (numbK > 1) { //длина числа > 1
                numbN /= 10
                numbK--
            }
            i = numbN % 10
            return i
        }
        return i
    }

    var curLen = 2
    var iPrev = 1
    var iCur = 1
    var iTemp = iPrev + iCur

    if (n <= 0) {
        println("Введено некорректное число")
    } else {
        if (n < 3) {
            return 1
        } else {
            while (curLen + lenOfInt(iTemp) < n) {
                curLen += lenOfInt(iTemp)
                iPrev = iCur
                iCur = iTemp
                iTemp = iPrev + iCur
            }
        }
    }
    return digFromInt(iTemp, n - curLen)
}

/*
var numbFib1 = 0
var numbFib2 = 1
var quantityInFib = 0
var scan = 0
var divisor = 0
var result = 1

while (scan < n) {
    val fibSum = numbFib1 + numbFib2
    numbFib1 = numbFib2
    numbFib2 = fibSum
    quantityInFib = 1
    divisor = 10
    while (numbFib1 / divisor != 0) {
        divisor *= 10
        quantityInFib += 1
    }
    scan += quantityInFib
}
scan -= quantityInFib
divisor /= 10
while (scan != n) {
    result = numbFib1 / divisor % 10
    divisor /= 10
    scan += 1
}
return result

 */