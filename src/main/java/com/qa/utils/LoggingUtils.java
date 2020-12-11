package com.qa.utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Nullable;

/**
 * @author Ankur Jaiswal
 * Common Utilily class to maintain log
 */
public class LoggingUtils {
    private Logger logger;

    /**
     * @param loggerClass - Class that is generating logs.
     */
    public LoggingUtils(Class loggerClass) {
        logger = LoggerFactory.getLogger(loggerClass.getName());
    }

    /**
     * This method will add info in logging and extent report
     * @param test - log to add into the extent report
     * @param message - Message to be added for logging
     */
    public void info(String message, @Nullable ExtentTest test) {
        logger.info(message);
        if (test != null) {
            test.log(LogStatus.INFO, message);
        }
    }

    /**
     * This method will add error in logging and extent report
     * @param test - log to add into the extent report
     * @param message - Message to be added for logging
     */
    public void error(String message, @Nullable ExtentTest test) {
        logger.error(message);
        if (test != null) {
            test.log(LogStatus.ERROR, message);
        }
    }

    /**
     * This method will add warning in logging and extent report
     * @param test - log to add into the extent report
     * @param message - Message to be added for logging
     */
    public void warning(String message, @Nullable ExtentTest test) {
        logger.warn(message);
        if (test != null) {
            test.log(LogStatus.WARNING, message);
        }
    }

    /**
     * This method will add info in logging and pass in extent report
     * @param test - log to add into the extent report
     * @param message - Message to be added for logging
     */
    public void pass(String message, @Nullable ExtentTest test) {
        logger.info(message);
        if (test != null) {
            test.log(LogStatus.PASS, message);
        }
    }
}
