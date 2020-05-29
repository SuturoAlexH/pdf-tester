package com.jPdfUnit.util;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.rendering.*;
import java.awt.image.*;
import java.io.*;

public class PdfUtil {

    public static BufferedImage toBufferedImage(final File pdfFile) throws IOException {
        PDDocument pd = PDDocument.load (pdfFile);
        PDFRenderer pr = new PDFRenderer (pd);
        BufferedImage bi = pr.renderImageWithDPI (0, 300);
        pd.close();
        return bi;
    }
}
