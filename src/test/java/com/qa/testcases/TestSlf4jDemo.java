package com.qa.testcases;

import com.qa.utils.Log;

public class TestSlf4jDemo {

    public static void main(String [] args){

        Log.startTestCase("TestSlf4jDemo_TestCase001");
        Log.info("This is logger test for INFO.");
        Log.debug("This is logger test for DEBUG");
        Log.error("This is logger test for ERROR");
        Log.endTestCase("TestSlf4jDemo_TestCase001");
    }
}

