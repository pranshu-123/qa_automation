package com.qa.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
    private static final LoggingUtils loggingUtils = new LoggingUtils(ImageUtils.class);
    /**
     * return true if both image files are equal else return false
     **/
    public static boolean compareImage(File fileA, File fileB) {
        // take buffer data from botm image files //
        BufferedImage imgA = null;
        BufferedImage imgB = null;
        try {
            imgA = ImageIO.read(fileA);
            imgB = ImageIO.read(fileB);
        } catch (IOException ae) {

        }
        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();

        // Checking whether the images are of same size or
        // not
        if ((width1 != width2) || (height1 != height2)) {
            loggingUtils.error("Image size does not match", null);
            return false;
        } else {

            // By now, images are of same size
            long difference = 0;

            // treating images likely 2D matrix

            // Outer loop for rows(height)
            for (int y = 0; y < height1; y++) {

                // Inner loop for columns(width)
                for (int x = 0; x < width1; x++) {

                    int rgbA = imgA.getRGB(x, y);
                    int rgbB = imgB.getRGB(x, y);
                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA) & 0xff;
                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB) & 0xff;

                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
                }
            }

            // Total number of red pixels = width * height
            // Total number of blue pixels = width * height
            // Total number of green pixels = width * height
            // So total number of pixels = width * height *
            // 3
            double total_pixels = width1 * height1 * 3;

            // Normalizing the value of different pixels
            // for accuracy

            // Note: Average pixels per color component
            double avg_different_pixels
                = difference / total_pixels;

            // There are 255 values of pixels in total
            double percentage
                = (avg_different_pixels / 255) * 100;

            // Lastly print the difference percentage
            System.out.println("Difference Percentage-->"
                + percentage);
            return (percentage < 5) ? true : false;
        }
    }

    /**
     * Check if an image contains color
     * @param image
     * @param rgb
     * @return
     */
    public static Boolean isImageContainsColor(File image, int[] rgb) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(image);
        } catch (IOException ae) {
            loggingUtils.info("Image not found.", null);
        }
        int width = img.getWidth();
        int height = img.getHeight();
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                int difference = 0;
                int rgbPixel = img.getRGB(x, y);
                int redA = (rgbPixel >> 16) & 0xff;
                int greenA = (rgbPixel >> 8) & 0xff;
                int blueA = (rgbPixel) & 0xff;
                difference += Math.abs(redA - rgb[0]);
                difference += Math.abs(greenA - rgb[1]);
                difference += Math.abs(blueA - rgb[2]);
                double total_pixels = width * height * 3;
                double avg_different_pixels = difference / total_pixels;
                double percentage = (avg_different_pixels / 255) * 100;
                if (percentage < 10) {
                    return true;
                }
            }
        }
        return false;
    }
}
