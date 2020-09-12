package com.qa.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
  int counter = 1;
  int retryMaxLimit = 3;

  @Override
  public boolean retry(ITestResult result) {
    if (counter < retryMaxLimit) {
      counter++;
      return true;
    } else {
        return false;
    }
  }
}
