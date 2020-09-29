package com.qa.pagefactory.clusters;
import java.lang.reflect.Method;

public class DataProviderClass {

  public static Object[][] getClusterIdsForClusterType(Method method) {
    if (method.getDeclaringClass().getPackage().getName().contains("impala")) {
      return new Object[][] {{"tnode54(CM)-wnode54(U)-TLS-Kerb-Sentry"}};
      //return new Object[][] {{"tnode54(CM)-wnode54(U)-TLS-Kerb-Sentry"}, {"tnode57(CM)-wnode26(U)-TLS-Kerb-Sentry"}};
      //return new Object[][] {{"Cluster1"}};
    }
    else {
      return new Object[][] {{"wnode25-HDP315-TLS-Kerb-Ranger"},{"wnode26-CDH633-TLS-Kerb-Sentry"}};
      //return new Object[][] {{"Cluster1 [296b...2ac9]"},{"Cluster1 [8e83...8441]"}};
    }
  }
}
