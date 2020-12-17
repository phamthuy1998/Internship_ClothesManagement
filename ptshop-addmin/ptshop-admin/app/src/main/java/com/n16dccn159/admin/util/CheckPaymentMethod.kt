package com.n16dccn159.admin.util

import java.lang.Integer.parseInt

fun isCreditCard(numberCard: String): Boolean {
    // Encoding only works on cards with less than 19 digits
    if (numberCard.length > 19)
        return false

    var sum = 0
    var mul = 1
    val l = numberCard.length
    var i = 0
    while (i < l) {
        val digit = numberCard.substring(l - i - 1, l - i)
        val tproduct = parseInt(digit, 10) * mul
        sum += if (tproduct >= 10)
            tproduct % 10 + 1
        else
            tproduct
        if (mul == 1)
            mul++
        else
            mul--
        i++
    }
// Uncomment the following line to help create credit card numbers
// 1. Create a dummy number with a 0 as the last digit
// 2. Examine the sum written out
// 3. Replace the last digit with the difference between the sum and
//    the next multiple of 10.

//  document.writeln("<BR>Sum      = ",sum,"<BR>");
//  alert("Sum      = " + sum);

    return sum % 10 == 0
}

fun isVisa(numberCard: String): Boolean {
    if (((numberCard.length == 16) || (numberCard.length == 13)) &&
        (numberCard.substring(0, 1) == "4")
    )
        return isCreditCard(numberCard)
    return false
}

fun isMaster(numberCard: String): Boolean {
    val firstDigit = numberCard.substring(0, 1).toInt()
    val secondDigit = numberCard.substring(1, 2).toInt()
    if ((numberCard.length == 16) && (firstDigit == 5) &&
        ((secondDigit >= 1) && (secondDigit <= 5))
    )
        return isCreditCard(numberCard)
    return false;
}

fun isAmericanExpress(numberCard: String): Boolean {
    val firstDigit = numberCard.substring(0, 1).toInt()
    val secondDigit = numberCard.substring(1, 2).toInt()
    if ((numberCard.length == 15) && (firstDigit == 3) &&
        ((secondDigit == 4) || (secondDigit == 7))
    )
        return isCreditCard(numberCard)
    return false;
}

fun isDinersClub(numberCard: String): Boolean {
    val firstDigit = numberCard.substring(0, 1).toInt()
    val secondDigit = numberCard.substring(1, 2).toInt()
    if ((numberCard.length == 14) && (firstDigit == 3) &&
        ((secondDigit == 0) || (secondDigit == 6) || (secondDigit == 8))
    )
        return isCreditCard(numberCard)
    return false;
}

fun isDiscover(numberCard: String): Boolean {
    val first4Digits = numberCard.substring(0, 4)
    if ((numberCard.length == 16) && (first4Digits == "6011"))
        return isCreditCard(numberCard)
    return false;

}

fun isUnionPay(numberCard: String): Boolean {
    val first4Digits = numberCard.substring(0, 4)
    if ((numberCard.length == 15) && (first4Digits == "2014" || first4Digits == "2149"))
        return isCreditCard(numberCard)
    return false;

}

fun isJCB(numberCard: String): Boolean {
    val first4Digits = numberCard.substring(0, 4)
    if ((numberCard.length == 16) &&
        (first4Digits == "3088" ||
                first4Digits == "3096" ||
                first4Digits == "3112" ||
                first4Digits == "3158" ||
                first4Digits == "3337" ||
                first4Digits == "3528")
    )
        return isCreditCard(numberCard)
    return false;

}

fun belongType(number: String): CheckPaymentMethodType {
    if (number.length >= 4) {
        when (number.substring(0, 4)) {
            "3088", "3096", "3112", "3158", "3337", "3528" -> {
                return CheckPaymentMethodType.JCB
            }
            "2014", "2149" -> {
                return CheckPaymentMethodType.UNION_PAY
            }
            "6011" -> {
                return CheckPaymentMethodType.DISCOVER
            }
        }
    } else {
        val firstDigit = number.substring(0, 1).toInt()
        val secondDigit = number.substring(1, 2).toInt()
        when (firstDigit) {
            3 -> {
                if (secondDigit == 4 || secondDigit == 7) return CheckPaymentMethodType.AMERICAN_EXPRESS
                if (secondDigit == 6 || secondDigit == 8 || secondDigit == 0) return CheckPaymentMethodType.DINERS_CLUB
            }
            4 -> {
                return CheckPaymentMethodType.VISA
            }
            5 -> {
                if (secondDigit in 1..5) return CheckPaymentMethodType.MASTER
            }
        }
    }
    return CheckPaymentMethodType.OTHERS
}

fun isType(brand: String): CheckPaymentMethodType {
    return when(brand){
        "Visa"-> CheckPaymentMethodType.VISA
        "Jcb"-> CheckPaymentMethodType.JCB
        else-> CheckPaymentMethodType.OTHERS
    }
}

enum class CheckPaymentMethodType {
    VISA, MASTER, AMERICAN_EXPRESS, DINERS_CLUB, JCB, DISCOVER, UNION_PAY, OTHERS
}