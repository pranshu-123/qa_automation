package com.qa.utils.actions;


import com.qa.utils.LoggingUtils;
import java.util.function.Supplier;

public class RetryExecuter<T> {
    private int retryCounter;
    private static final int MAX_RETRIES = 3;
    private LoggingUtils logger = new LoggingUtils(RetryExecuter.class);

    // Takes a function and executes it, if fails, passes the function to the retry command
    public T run(Supplier<T> function) {
        try {
            return function.get();
        } catch (Exception e) {
            return retry(function);
        } catch (Error err) {
            return retry(function);
        }
    }

    public int getRetryCounter() {
        return retryCounter;
    }

    private T retry(Supplier<T> function) throws RuntimeException {
        logger.error("FAILED - Execution failed, will be retried " + MAX_RETRIES + " times.", null);
        retryCounter = 0;
        while (retryCounter < MAX_RETRIES) {
            try {
                return function.get();
            } catch (Throwable ex) {
                retryCounter++;
                logger.warning("FAILED - Execution failed on retry " + retryCounter + " of " + MAX_RETRIES + " error: " + ex,
                    null);
                if (retryCounter >= MAX_RETRIES) {
                    logger.error("Max retries exceeded.", null);
                    break;
                }
            }
        }
        throw new RuntimeException("Execution failed on all of " + MAX_RETRIES + " retries");
    }
}
