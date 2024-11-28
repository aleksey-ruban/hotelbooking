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

}
