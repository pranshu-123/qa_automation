package com.qa.pagefactory.clusters;

import java.lang.reflect.Method;

/**
 * @author Ankur Jaiswal
 * This class contains data provider methods which will provide data to
 * the test cases on run time.
 */
public class DataProviderClass {
    /**
     * This method returns cluster ids on which test cases will be executed
     * @param method - Reflection @test method
     * @return - List of clusterIds
     */
    public static Object[][] getClusterIdsForClusterType(Method method) {
        /* @TODO - Need to remove hard coded values from cluster Ids this should be parameterized
           or it should be pulled from APIs. */
        if (method.getDeclaringClass().getPackage().getName().contains("impala")) {
            return new Object[][]{{"tnode54(CM)-wnode54(U)-TLS-Kerb-Sentry"}};
        } else {
            return new Object[][]{{"wnode25-HDP315-TLS-Kerb-Ranger"}, {"wnode26-CDH633-TLS-Kerb-Sentry"}};
        }
    }
}
