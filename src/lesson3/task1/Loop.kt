@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

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
    var result = 0
    var number = abs(n)
    return if (n == 0) 1
    else {
        while (number > 0) {
            number /= 10
            result++
        }
        result
    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var firstFib = 1
    var secondFib = 1
    var fibN = 0
    if ((n == 1) || (n == 2)) return 1
    else {
        for (i in 1..n - 2) {
            fibN = firstFib + secondFib
            firstFib = secondFib
            secondFib = fibN

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
    var numberN = n
    var numberM = m
    while (numberM != 0 && numberN != 0){
        if (numberM >= numberN) numberM %= numberN
        else numberN %= numberM
    }
  return (n * m) / (numberM + numberN)
}
/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var divisor = n
    val endOfCycle = round(sqrt(n.toDouble())).toInt()
    for (i in 2..endOfCycle) {
        if ((divisor % i == 0) && (i < divisor)) divisor = i
    }
    return divisor
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
fun isCoPrime(m: Int, n: Int): Boolean = lcm(m, n) / (n * m) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val numberM = ceil(sqrt(m.toDouble())).toInt()
    val numberN = ceil(sqrt(n.toDouble())).toInt()
    return (sqrt(m.toDouble()) % 1.0 == 0.0) ||
            (numberM != numberN)
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var powerOfX = 1.0
    var term = 1.0
    var count = 1
    var sinX = 0.0
    val elemX = x % (2.0 * PI)
    while (abs(term) >= eps) {
        var parts = pow(elemX, powerOfX) / factorial(powerOfX.toInt())
        term = if (count % 2 != 0) parts
        else -1.0 * parts
        sinX += term
        powerOfX += 2.0
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
fun cos(x: Double, eps: Double): Double = sin(x + PI / 2.0)
/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var number = n
    var result = 0
    while (number != 0) {
        result = result * 10 + (number % 10)
        number /= 10
    }
    return result
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
    var number = n
    var a = number % 10
    var b = number % 100 / 10
    while ((a == b) && (number >= 10)) {
            number /= 10
            b = number % 100 / 10
        }
        return number > 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun pos(n: Int, position: Int): Int {
    var i = 1
    var number = n
    while (i != position) {
        number /= 10
        i++
    }
    return number % 10
}
fun squareSequenceDigit(n: Int): Int {
    var numbers = 0
    var sumOfNumbers = 0
    var i = 1
    while (sumOfNumbers < n) {
        numbers = i * i
        sumOfNumbers += digitNumber(numbers)
        i++
    }
    return if (sumOfNumbers > n) pos(numbers, sumOfNumbers - n + 1)  else
        numbers % 10

}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var numbers = 0
    var sumOfNumbers = 0
    var i = 1
    while (sumOfNumbers < n) {
        numbers = fib(i)
        sumOfNumbers += digitNumber(numbers)
        i++
    }
    return if (sumOfNumbers > n) pos(numbers, sumOfNumbers - n + 1)  else
        numbers % 10

}
    /*for (i in 1..n) {
        str = fib(i).toString()
        result.append(str)
    }
    return (result[n-1]).toInt() - translateTNumber
*/
