package com.qa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private static final Logger logger = LoggerFactory.getLogger(Log.class.getName());

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public static void startTestCase(String sTestCaseName){

        Log.info("****************************************************************************************");

        Log.info("****************************************************************************************");

        Log.info("$$$$$$$$$$$$$$$$$$$$$        "+sTestCaseName+ "        $$$$$$$$$$$$$$$$$$$$$$$$$");

        Log.info("****************************************************************************************");

        Log.info("****************************************************************************************");

    }

    //This is to print log for the ending of the test case
    public static void endTestCase(String sTestCaseName){

        Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");
    }

    public static void info(String message){
        logger.info(message);
    }

    public static void debug(String message){
        logger.debug(message);
    }

    public static void error(String message){
        logger.error(message);
    }
}
