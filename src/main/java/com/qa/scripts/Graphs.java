package com.qa.scripts;

import com.qa.pagefactory.GraphsPageObject;
import com.qa.scripts.data.Forecasting;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ojasvi Pandey This class contains all Graphs KPI on UI related action
 * methods
 */

public class Graphs {

    private static final Logger LOGGER = Logger.getLogger(Forecasting.class.getName());
    private final WebDriver driver;
    public GraphsPageObject graphsPageObject;
    public WaitExecuter waitExecuter;

    public Graphs(WebDriver driver) {
        this.driver = driver;
        graphsPageObject = new GraphsPageObject(driver);
        waitExecuter = new WaitExecuter(driver);

    }

    /* Get color value from footer of the graph line generated on graph */
    public List<String> getRGBFromFooter(List<WebElement> listWebelementOfAppName,
                                         List<WebElement> listWebelementOfRGB) {

        // Empty array to add rgbValue
        List<String> rgbList = new ArrayList<String>();
        // Iterate the list to get the rgb value
        for (int i = 0; i < listWebelementOfAppName.size(); i++) {
            String styleTagValue = listWebelementOfRGB.get(i).getAttribute("style");
            String[] arrayOfStyleTagValues = styleTagValue.split(":");
            String rgbValueWithSemiColon = arrayOfStyleTagValues[2];
            String rgbValue = rgbValueWithSemiColon.replaceAll(";", "");
            // Trim the whitespaces
            rgbList.add(rgbValue.trim());
            LOGGER.info("Name of application in graph and their RGB value "
                    + listWebelementOfAppName.get(i).getText() + " - " + rgbValue);
        }
        return rgbList;
    }

    /* Convert the hexcode to RGB color dimension */
    public String convertHexcolorToRGB(String hashColorString) {
        int r = Integer.valueOf(hashColorString.substring(1, 3), 16);
        int g = Integer.valueOf(hashColorString.substring(3, 5), 16);
        int b = Integer.valueOf(hashColorString.substring(5, 7), 16);
        String rgbFinalValue = ("rgb(" + r + ", " + g + ", " + b + ")");
        System.out.println("The #Codevalue " + hashColorString + " conversion to RGB is: " + rgbFinalValue);
        return rgbFinalValue;
    }

    /* Get hashcode of the graph generated */
    public List<String> getHashColorFromGraph(List<WebElement> hashColorCodeList) {
        List<String> hashColorList = new ArrayList<String>();

        // Iterate to get each element from hashColorCodeList
        for (WebElement element : hashColorCodeList) {
            String hashColor = element.getAttribute("fill");
            hashColorList.add(hashColor);
        }
        return hashColorList;
    }

    /* Check the By Type graph is generated */
    public Boolean validateByTypeGraphIsGenerated() {

        //Get RGB value list
        List<String> rgbValueList = getRGBFromFooter(graphsPageObject.listOfAppNameFromFooter, graphsPageObject.listOfRGBFromFooter);

        //Get hash color list
        List<String> hashColorList = getHashColorFromGraph(graphsPageObject
                .getChildElement(graphsPageObject.parentTagOfByTypeHexcode, graphsPageObject.childTagsOfByTypeHexcode));

        List<String> hexColorToRGBList = new ArrayList<String>();
        Boolean compareRGBToHashColor;

        //Iterate to get each hashcolor and convert to RGB
        for (String hashColor : hashColorList) {
            String hexColorToRGB = convertHexcolorToRGB(hashColor);
            hexColorToRGBList.add(hexColorToRGB);
        }

        /* Compare the #code value to rgb */
        compareRGBToHashColor = rgbValueList.equals(hexColorToRGBList);

        return compareRGBToHashColor;
    }

    /* Generic method to validate if the graph is present
     * @listWebelementOfAppName - App Names from footer of graph element to be passed
     * @listWebelementOfRGB - RGB tags from footer of the graph
     * @hashColorCodeList - Hash coding to be passed from graph element*/
    public Boolean validateGraphIsGenerated(List<WebElement> listWebelementOfAppName,
                                            List<WebElement> listWebelementOfRGB, List<WebElement> hashColorCodeList) {
        //Get RGB value list
        List<String> rgbValueList = getRGBFromFooter(listWebelementOfAppName, listWebelementOfRGB);
        List<String> hashColorList = new ArrayList<String>();
        // Iterate to get each element from hashColorCodeList
        for (WebElement element : hashColorCodeList) {
            String hashColor = element.getAttribute("stroke");
            hashColorList.add(hashColor);
        }
        List<String> hexColorToRGBList = new ArrayList<String>();
        Boolean compareRGBToHashColor;
        LOGGER.info("Hash code values from graph " + hashColorList);
        //Iterate to get each hashcolor and convert to RGB
        for (String hashColor : hashColorList) {
            String hexColorToRGB = convertHexcolorToRGB(hashColor);
            LOGGER.info("HASHCOLOR - " + hashColor + " hexColorToRGB - " + hexColorToRGB);
            hexColorToRGBList.add(hexColorToRGB);
        }
        LOGGER.info("ALL HEX COLOR TO RGBS - " + hexColorToRGBList);
        /* Compare the #code value to rgb */
        compareRGBToHashColor = rgbValueList.equals(hexColorToRGBList);
        return compareRGBToHashColor;
    }

    /*Return RGB values for defined elements
     * @allRGBElementTags - RGB WebElement List
     * @splitBy - Regex to split the data by
     * @attributeName - Get value from defined tag*/
    public List<String> getRGBValuesFromElement(List<WebElement> allRGBElementTags, String splitBy, String attributeName) {
        List<WebElement> rgbTags = allRGBElementTags;
        List<String> getAllAttributes = new ArrayList<>();
        List<String> rgbFinalValues = new ArrayList<String>();
        for (WebElement rgbTag : rgbTags) {
            getAllAttributes.add(rgbTag.getAttribute(attributeName));
        }
        LOGGER.info("All attributes of defined tag- " + getAllAttributes);
        for (String attributeValue : getAllAttributes) {
            rgbFinalValues.add(attributeValue.split(splitBy, 1)[1].trim());
            LOGGER.info("RGB value- " + attributeValue.split(splitBy, 1)[1].trim());
        }
        LOGGER.info("Final RGB values derived- " + rgbFinalValues);
        return rgbFinalValues;
    }
}
