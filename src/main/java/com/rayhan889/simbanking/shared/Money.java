package com.rayhan889.simbanking.shared;

import java.util.regex.Pattern;

public record Money(long minorUnits, String currency) {

    private static final Pattern CURRENCY = Pattern.compile("^[A-Z]{3}$");

    public Money {
        if (currency == null || !CURRENCY.matcher(currency).matches()) {
            throw new IllegalArgumentException("currency must be a 3-letter uppercase code");
        }
    }

    public static Money zero(String currency) {
        return new Money(0, currency);
    }

    public Money plus(Money other) {
        requireSameCurrency(other);
        return new Money(Math.addExact(minorUnits, other.minorUnits), currency);
    }

    public Money minus(Money other) {
        requireSameCurrency(other);
        return new Money(Math.subtractExact(minorUnits, other.minorUnits), currency);
    }

    public Money negate() {
        return new Money(Math.negateExact(minorUnits), currency);
    }

    public boolean isPositive() {
        return minorUnits > 0;
    }

    private void requireSameCurrency(Money other) {
        if (!currency.equals(other.currency)) {
            throw new ApiException(ErrorCode.CURRENCY_MISMATCH, "Cannot combine " + currency + " with " + other.currency);
        }
    }
}
