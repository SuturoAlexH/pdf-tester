package com.jPdfUnit.asserts;

import com.jPdfUnit.util.ImageUtil;
import com.jPdfUnit.util.PdfUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.assertj.core.api.AbstractAssert;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Assertion for pdf files.
 */
public class PdfAssert extends AbstractAssert<PdfAssert, File> {

    PdfAssert(File actual) {
        super(actual, PdfAssert.class);
    }

    /**
     *
     * @param actual
     * @return
     */
    public static PdfAssert assertThat(File actual) {
        return new PdfAssert(actual);
    }

    /**
     * Counts the number of pages of the pdf document and compares it
     * with the #expectedPageCount. If the number of pages are different
     * the method fails.
     *
     * @param expectedPageCount the expected page count
     * @return the pdf assertion
     */
    public PdfAssert hasNumberOfPages(final int expectedPageCount) {
        isNotNull();

        try (PDDocument doc = PDDocument.load(actual)){
            int actualPageCount = doc.getNumberOfPages();
            if(actualPageCount != expectedPageCount){
                failWithMessage("Expected number of pages %s but was %s", expectedPageCount, actualPageCount);
            }
        } catch (IOException e) {
            failWithMessage("Cant load document with path %s", actual.getAbsoluteFile());
        }

        return this;
    }

    /**
     * Compares the #referencePdfDocument with the pdf file and
     * fails if they have a different appearance.
     *
     * @param referencePdfDocument the reference document
     * @return the pdf assertion
     */
    public PdfAssert hasSameAppearanceAs(final File referencePdfDocument) {
        isNotNull();

        try {
            BufferedImage documentImage = PdfUtil.toBufferedImage(actual);
            BufferedImage referenceDocumentImage = PdfUtil.toBufferedImage(referencePdfDocument);

            if(!ImageUtil.equals(documentImage, referenceDocumentImage)){
                failWithMessage("Documents are not equal");
            }
        } catch (IOException e) {
            failWithMessage("Cant load document with path %s", actual.getPath());
        }

        return this;
    }
}
