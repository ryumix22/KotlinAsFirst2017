@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import lesson1.task1.sqr
import java.lang.Math.*

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
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
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
    for (m in 2..n/2) {
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
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var res = 0
    var num = n
    return if (n == 0) 1
    else {
        while (num > 0) {
            num /= 10
            res++
        }
        res
    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var secFib = 1
    var thirdFib = 2
    var fibN = 0
    if ((n == 1) || (n == 2)) return 1
    if (n == 3) return 2
    else {
        for (i in 1..n - 3) {
            fibN = secFib + thirdFib
            secFib = thirdFib
            thirdFib = fibN

        }
    }
    return fibN
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var numN = n
    var nunM = m
    while (numN != nunM) {
        if (nunM < numN) numN -= nunM
        else nunM -= numN
    }
    return n / nunM * m
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var devisor = n
    val endOfCycle = Math.sqrt(n.toDouble()).toInt()
    for (i in 2..endOfCycle) {
        if ((devisor % i == 0) && (i < devisor)) devisor = i
    }
    return devisor
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    val divisor = n
    var maxDivisor = n / 2
    while (divisor % maxDivisor != 0) maxDivisor -= 1
    return maxDivisor
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var numM = m
    var numN = n
    while (numM != numN) {
        if (numM > numN) numM -= numN
        else numN -= numM
    }
    return (numN == 1)
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var res = 0
    for (i in m..n) {
        if (Math.sqrt(i.toDouble()) % 1.0 == 0.0) res = i
    }
    return res != 0
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var powOfX = 1.0
    var term = 1.0
    var count = 1
    var sinX = 0.0
    val elemX = if ((x / PI) % 1.0 == 0.0) 0.0 else x
    while (abs(term) >= eps){
        term = if (count % 2 != 0) pow(elemX, powOfX) /  factorial(powOfX.toInt())
        else -1.0 * pow(elemX, powOfX) /  factorial(powOfX.toInt())
        sinX += term
        powOfX += 2.0
        count += 1

    }
    return sinX
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var powOfX = 2.0
    var term = 1.0
    var count = 1
    var cosX = 1.0
    val elemX = if ((x / PI) % 1.0 == 0.0) if ((x / PI) % 2 == 0.0) 2 * PI else PI
    else x
    while (abs(term) >= eps){
        term = if (count % 2 != 0) -1.0 * pow(elemX, powOfX) /  factorial(powOfX.toInt())
        else pow(elemX, powOfX) /  factorial(powOfX.toInt())
        cosX += term
        powOfX += 2.0
        count += 1

    }
    return cosX
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var num = n
    var res = 0
    while (num != 0) {
        res = res * 10 + (num % 10)
        num /= 10
    }
    return res
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = n
    var a = num % 10
    var b = num % 100 / 10
    if (n < 10) return false
        while ((a == b) && (num != 0)) {
            a = b
            b = num % 10
            num /= 10
        }
        return (num != 0)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var str: String
    var res = ""
    for (i in 1..n) {
        str = (i * i).toString()
        res += str
    }
    return ((res[n-1]).toInt() - 48)
}
/* Я пытался сделать таким образом, но у меня была ошибка при n=10, и так же при n>10 тест не останавливался
 {
    var sqrs = 1
    var countOfAllSqr: Int
    var countOfAllNum = 0
    var res = 0
    while (b < n) {
        countOfAllSqr = digitNumber(sqrs * sqrs)
        res = res * (pow(10.0, countOfAllSqr.toDouble())).toInt() + sqrs * sqrs
        countOfAllNum = digitNumber(res)
        sqrs += 1
    }
    return if (countOfAllNum == n) res % 10
    else (res % (pow(10.0, (countOfAllNum - n + 1.0)).toInt())) / 10
}
*/

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
