package com.github.aleksey_ruban.hotelbooking.helpers;

public class PhoneNumberHelper {

    public static boolean isValid(String phoneNumber) {
        long count = phoneNumber.chars()
                .filter(Character::isDigit)
                .count();
        if (phoneNumber.startsWith("+7")) {
            return count == 11;
        } else {
            if (phoneNumber.startsWith("8")) {
                return count == 11 || count == 10;
            } else return count == 10;
        }
    }

    public static String normalizePhoneNumber(String phoneNumber) {
        String digitalNumber = phoneNumber.chars()
                .filter(Character::isDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();

        long count = phoneNumber.chars()
                .filter(Character::isDigit)
                .count();
        if (count == 11) {
            return digitalNumber.substring(1);
        } else {
            return digitalNumber;
        }
    }

    public static String prettyPhoneNumber(String rawNumber) {
        if (rawNumber == null || rawNumber.length() != 10 || !rawNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Некорректный номер телефона. Ожидается строка из 10 цифр.");
        }
        return String.format("+7 (%s) %s-%s-%s",
                rawNumber.substring(0, 3),
                rawNumber.substring(3, 6),
                rawNumber.substring(6, 8),
                rawNumber.substring(8)
        );
    }

}
