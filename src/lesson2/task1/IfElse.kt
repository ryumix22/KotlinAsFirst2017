@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson4.task1.abs

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
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if (age <= 10 || age in 20..99 || age in 120..200 || age in 100..110) return when {
        age % 10 == 1 -> "$age год"
        age % 10 in 2..4 -> "$age года"
        else -> "$age лет"
    }
    if (age in 11..19 || age in 111..119) return "$age лет"
    else return "Возраста $age нет"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = (s1 + s2 + s3) / 2
    if (s <= s1) return s / v1
    if (s <= s1 + s2) return t1 + (s - s1) / v2
    return if (s < s1 + s2 + s3) t1 + t2 + (s - (s1 + s2)) / v3
    else 0.0
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
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    if ((rookX1 == kingX || rookY1 == kingY) && (rookX2 == kingX || rookY2 == kingY)) return 3
    if (rookX1 == kingX || rookY1 == kingY) return 1
    if (rookX2 == kingX || rookY2 == kingY) return 2
    else return 0
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
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val xdist = Math.abs(kingX - bishopX)
    val ydist = Math.abs(kingY - bishopY)
    if ((rookX == kingX || rookY == kingY) && (xdist == ydist)) return 3
    if (rookX == kingX || rookY == kingY) return 1
    if (xdist == ydist) return 2
    else return 0

}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if (a + b <= c || c + b <= a || c + a <=b) return -1
    else if (sqr(a) == sqr(b) + sqr(c) || sqr(b) == sqr(a) + sqr(c) || sqr(c) == sqr(a) + sqr(b))  return 1
    else if (sqr(a) > sqr(b) + sqr(c) || sqr(b) > sqr(a) + sqr(c) || sqr(c) > sqr(b) + sqr(a)) return 2
    else return 0
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (a <= c) {
        if (b < c) return -1
        if (b == c) return 0
        if ((b > c) && (b < d)) return b - c
        else return d - c
    }
    if (a > c) {
        if (d < a) return -1
        if (d == a) return 0
        if ((d > a) && (d < b)) return d - a
        else return b - a
    }
    else return -1
}