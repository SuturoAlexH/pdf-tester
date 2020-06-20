package com.jPdfUnit.util;

import java.awt.image.BufferedImage;

/**
 * This is a util class for images.
 */
public class ImageUtil {

    /**
     * Compares two images pixel by pixel.
     *
     * @param image1 the first image.
     * @param image2 the second image.
     * @return true if the images are the same and false otherwise
     */
    public static boolean equals(BufferedImage image1, BufferedImage image2) {
        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
            return false;
        }

        int width  = image1.getWidth();
        int height = image1.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }
}
