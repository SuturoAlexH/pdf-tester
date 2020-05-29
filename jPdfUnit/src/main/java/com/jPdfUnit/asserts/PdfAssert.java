package com.jPdfUnit.asserts;

import com.jPdfUnit.util.ImageUtil;
import com.jPdfUnit.util.PdfUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.assertj.core.api.AbstractAssert;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfAssert extends AbstractAssert<PdfAssert, File> {

    public PdfAssert(File actual) {
        super(actual, PdfAssert.class);
    }

    public static PdfAssert assertThat(File actual) {
        return new PdfAssert(actual);
    }

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
