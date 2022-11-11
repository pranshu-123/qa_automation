package com.qa.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Pranshu
 * Random generator of data
 */
public class RandomGenerator {
    private static final LoggingUtils LOGGER = new LoggingUtils(RandomGenerator.class);

    /**
     * Generate random email ID
     * @return random email id
     */
    public static String generateRandomEmail() {
        LOGGER.info("Generating a Random email String", null);
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-";
        String email = "";
        String temp = RandomStringUtils.random(20, allowedChars);
        email = temp.substring(0, temp.length() - 9) + "@unraveltest.com";
        return email;
    }


    /**
     * Generate random name
     * @return random name
     */
    public static String generateRandomName() {
        LOGGER.info("Generating a Random email String", null);
        String allowedChars = "abcdefghijklmnopqrstuvwxyz";
        String name = "";
        String temp = RandomStringUtils.random(15, allowedChars);
        name = "unravel" + "-" +temp.substring(0, temp.length() - 9);
        return name;
    }
}
