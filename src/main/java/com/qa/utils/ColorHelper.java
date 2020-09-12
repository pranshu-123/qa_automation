package com.qa.utils;

import java.awt.*;

/**
 * @author Ankur Jaiswal
 * This is helper class which contains utility methods of colors
 */
public class ColorHelper {

  /**
   * This method find the percentage difference between two colors
   * @param c1 Color Object 1
   * @param c2 Color Object 2
   * @return float value which has percentage difference
   */
  public static float findPercentDifference(Color c1, Color c2) {
    int diffRed   = Math.abs(c1.getRed()   - c2.getRed());
    int diffGreen = Math.abs(c1.getGreen() - c2.getGreen());
    int diffBlue  = Math.abs(c1.getBlue()  - c2.getBlue());

    float pctDiffRed   = (float)diffRed   / 255;
    float pctDiffGreen = (float)diffGreen / 255;
    float pctDiffBlue   = (float)diffBlue  / 255;

    return (pctDiffRed + pctDiffGreen + pctDiffBlue) / 3 * 100;
  }

  public static String convertHexToRGB(String hexcode) {
    int r = Integer.valueOf(hexcode.substring(1, 3), 16);
    int g = Integer.valueOf(hexcode.substring(3, 5), 16);
    int b = Integer.valueOf(hexcode.substring(5, 7), 16);
    return r + "," + g + "," + b;
  }
}
