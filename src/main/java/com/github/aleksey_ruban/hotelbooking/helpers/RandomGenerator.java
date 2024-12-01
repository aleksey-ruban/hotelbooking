package com.github.aleksey_ruban.hotelbooking.helpers;

import java.util.Random;

public class RandomGenerator {

    public static String generateClientToken() {
        Random random = new Random();
        int randomCode = random.nextInt(1000000);
//        return String.format("%06d", randomCode);
        return "000000";
    }

}
