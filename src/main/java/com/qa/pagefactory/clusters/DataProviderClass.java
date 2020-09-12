package com.qa.pagefactory.clusters;
import java.lang.reflect.Method;

public class DataProviderClass {

  public static Object[][] getClusterIdsForClusterType(Method method) {
    if (method.getDeclaringClass().getPackage().getName().contains("impala.chargeback")) {
      return new Object[][] {{"tnode54(CM)-wnode54(U)-TLS-Kerb-Sentry"}, {"tnode57(CM)-wnode26(U)-TLS-Kerb-Sentry"}};
      //return new Object[][] {{"Cluster1"}};
    }else if (method.getDeclaringClass().getPackage().getName().contains("impala.resources")){
        return new Object[][] {{"tnode54(CM)-wnode54(U)-TLS-Kerb-Sentry"}, {"tnode57(CM)-wnode26(U)-TLS-Kerb-Sentry"}};
    }else {
      return new Object[][] {{"Cluster1 [3908...1450]"},{"Cluster1 [8d95...fe10]"}};
      //return new Object[][] {{"Cluster1 [296b...2ac9]"},{"Cluster1 [8e83...8441]"}};
      //return new Object[][] {{"Cluster1 [296b...2ac9]"}, {"Cluster1 [f5a6...2ac9]"}, {"Cluster1 [8e83...8441]"}};
    }
  }
}
