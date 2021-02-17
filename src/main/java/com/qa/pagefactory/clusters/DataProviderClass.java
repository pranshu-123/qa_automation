package com.qa.pagefactory.clusters;

import com.qa.io.UnravelConfigYamlReader;

import java.lang.reflect.Method;
import java.util.Iterator;

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
    public static Iterator<Object[]> getClusterIdsForClusterType(Method method) {
        /* @TODO - Need to remove hard coded values from cluster Ids this should be parameterized
           or it should be pulled from APIs. */
        UnravelConfigYamlReader unravelConfigYamlReader = new UnravelConfigYamlReader();
        if (method.getDeclaringClass().getPackage().getName().contains("impala")
                || method.getDeclaringClass().getPackage().getName().contains("mapreduce") ) {
            return unravelConfigYamlReader.getClusterList(true);
        } else if(method.getDeclaringClass().getPackage().getName().contains("hbase")) {
            return unravelConfigYamlReader.getClusterList("hbase");
        }
        else {
            return unravelConfigYamlReader.getClusterList(false);
        }
    }
}


//return unravelConfigYamlReader.getClusterList(true);
//return unravelConfigYamlReader.getClusterList(true, "hbase");