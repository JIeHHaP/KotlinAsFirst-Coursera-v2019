@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Integer.min
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val a: String
    val age10 = age % 10
    val age100 = age % 100
    a = if (age10 == 1 && age100 != 11) {
        " год"
    } else if (age10 in 2..4 && age100 !in 12..14) {
        " года"
    } else {
        " лет"
    }
    return "$age" + a
}


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val d1 = t1 * v1
    val d2 = t2 * v2
    val d3 = t3 * v3
    val hwt: Double
    val halfDistance = (d1 + d2 + d3) / 2
    hwt = if (halfDistance <= d1) {
        halfDistance / v1
    } else if (halfDistance > d1 && halfDistance <= d1 + d2) {
        t1 + (halfDistance - d1) / v2
    } else {
        t1 + t2 + (halfDistance - d1 - d2) / v3
    }
    return hwt

}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var fight = 0
    when {
        (kingX != rookX1 && kingY != rookY1) && (kingX != rookX2 && kingY != rookY2) -> fight = 0
        (kingX == rookX1 || kingY == rookY1) && (kingX != rookX2 && kingY != rookY2) -> fight = 1
        (kingX != rookX1 && kingY != rookY1) && (kingX == rookX2 || kingY == rookY2) -> fight = 2
        (kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2) -> fight = 3
    }
    return fight
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {

    return when {
        (kingX == rookX || kingY == rookY) && (abs(bishopX - kingX) != abs(bishopY - kingY)) -> 1
        (kingX != rookX && kingY != rookY) && (abs(bishopX - kingX) == abs(bishopY - kingY)) -> 2
        (kingX == rookX || kingY == rookY) && (abs(bishopX - kingX) == abs(bishopY - kingY)) -> 3
        else -> 0
    }

}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var maxD = 0.0
    var secD = 0.0
    var thrD = 0.0

    when {
        a >= b && a >= c -> {
            maxD = a
            if (b > c) {
                secD = b
                thrD = c
            } else {
                secD = c
                thrD = b
            }
        }
        b >= a && b >= c -> {
            maxD = b
            if (a > c) {
                secD = a
                thrD = c
            } else {
                secD = c
                thrD = a
            }
        }
        c >= a && c >= b -> {
            maxD = c
            if (a > b) {
                secD = a
                thrD = b
            } else {
                secD = b
                thrD = a
            }
        }
    }

    val oxygen = (maxD * maxD) < ((secD * secD) + (thrD * thrD))
    val rightTriangle = (maxD * maxD) == ((secD * secD) + (thrD * thrD))
    val obtuseTriangle = (maxD * maxD) > ((secD * secD) + (thrD * thrD))


    return if ((maxD < secD + thrD) && (maxD > secD - thrD)) {
        when {
            oxygen -> 0
            rightTriangle -> 1
            obtuseTriangle -> 2
            else -> 999
        }
    } else -1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return if (a <= b && c <= d && b >= c && a < d) {
        abs(max(a, c) - min(b, d))
    } else -1
}



