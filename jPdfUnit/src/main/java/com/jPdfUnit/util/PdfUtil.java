package com.jPdfUnit.util;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.rendering.*;
import java.awt.image.*;
import java.io.*;

/**
 * This is a util class for pdf files.
 */
public class PdfUtil {

    /**
     * Transform a pdf file to an image.
     *
     * @param pdfFile the pdf file
     * @return the pdf file as image
     * @throws IOException if something went wrong while conversion
     */
    public static BufferedImage toBufferedImage(final File pdfFile) throws IOException {
        PDDocument pd = PDDocument.load(pdfFile);
        PDFRenderer pr = new PDFRenderer(pd);
        BufferedImage bi = pr.renderImageWithDPI (0, 300);
        pd.close();
        return bi;
    }
}
