package com.qa.base;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Ankur Jaiswal
 * This class contains all testNg suite related utilities.
 */
public class TestNGSuiteManager {

    /**
     * Create testNG suites with passed classes in
     * @param classes
     */
    public void createTestNGSuite(Set<Class> classes) {
        TestNG myTestNG = new TestNG();
        //Create an instance of XML Suite and assign a name for it.
        XmlSuite mySuite = new XmlSuite();
        mySuite.setName("UnravelUISuite");

        //Create an instance of XmlTest and assign a name for it.
        XmlTest myTest = new XmlTest(mySuite);
        myTest.setName("UnravelTest");

        //Create a list which can contain the classes that you want to run.
        List<XmlClass> myClasses = new ArrayList<XmlClass>();
        for (Class testClass : classes) {
            myClasses.add(new XmlClass(testClass));
        }
        //Assign that to the XmlTest Object created earlier.
        myTest.setXmlClasses(myClasses);

        //Create a list of XmlTests and add the Xmltest you created earlier to it.
        List<XmlTest> myTests = new ArrayList<XmlTest>();
        myTests.add(myTest);

        //add the list of tests to your Suite.
        mySuite.setTests(myTests);

        System.out.println(mySuite.toXml());
        //Add the suite to the list of suites.
        List<XmlSuite> mySuites = new ArrayList<>();
        mySuites.add(mySuite);
        //Set the list of Suites to the testNG object you created earlier.
        myTestNG.setXmlSuites(mySuites);
        myTestNG.run();
    }
}
