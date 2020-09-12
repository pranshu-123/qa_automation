package com.qa.scripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.GraphsPageObject;
import com.qa.utils.WaitExecuter;

/**
 * @author Ojasvi Pandey This class contains all Graphs KPI on UI related action
 *         methods
 */

public class Graphs {

	private WebDriver driver;
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
			System.out.println("Name of application in graph and their RGB value "
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

	/* Check the By Status graph is generated */
	public Boolean validateByTypeGraphIsGenerated() {

		//Get RGB value list
		List<String> rgbValueList = getRGBFromFooter(graphsPageObject.listOfAppNameFromFooter,graphsPageObject.listOfRGBFromFooter);

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

}
