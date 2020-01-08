package com.datalogics.pdf.samples.manipulation;

import com.adobe.internal.io.ByteReader;
import com.adobe.internal.io.ByteWriter;
import com.adobe.internal.io.InputStreamByteReader;
import com.adobe.internal.io.RandomAccessFileByteWriter;
import com.adobe.pdfjt.pdf.document.PDFDocument;
import com.adobe.pdfjt.pdf.document.PDFOpenOptions;
import com.adobe.pdfjt.pdf.document.PDFSaveLinearOptions;
import com.adobe.pdfjt.pdf.document.PDFSaveOptions;
import com.adobe.pdfjt.services.pdfa.PDFAConformanceLevel;
import com.adobe.pdfjt.services.pdfa.PDFADefaultValidationHandler;
import com.adobe.pdfjt.services.pdfa.PDFAService;
import com.adobe.pdfjt.services.pdfa.PDFAValidationOptions;
import com.adobe.pdfjt.services.sanitization.SanitizationOptions;
import com.datalogics.pdf.samples.SampleTest;
import com.datalogics.pdf.samples.util.DocumentUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * Test the ConvertPdfDocument sample.
 */
public class SavedLinearizedTest extends SampleTest {

    private static final String FILE_NAME = "InteractivityRemoved.pdf";

    @Test
    public void testSaveLinearizedTest() throws Exception {
        final URL inputUrl = SaveLinearized.class.getResource(SaveLinearized.INPUT_PDF_PATH);
        final File outputFile = newOutputFileWithDelete(FILE_NAME);
        final URL outputUrl = outputFile.toURI().toURL();

        InputStream ins = new FileInputStream(new File("/Users/coreyyates/Downloads/PDFJT/PDFJT-1273/PDFJT-1273.pdf"));
        ByteReader br = new InputStreamByteReader(ins);
        PDFDocument doc = PDFDocument.newInstance(br, PDFOpenOptions.newInstance());
        final PDFSaveOptions saveOptions = PDFSaveLinearOptions.newInstance();
        final SanitizationOptions options = new SanitizationOptions();
        options.setSaveOptions(saveOptions);
        final RandomAccessFile outputPdfFile = new RandomAccessFile(new File(FILE_NAME), "rw");
        ByteWriter writer = new RandomAccessFileByteWriter(outputPdfFile);
        doc.saveAndClose(writer, saveOptions);

        // Make sure the Output file exists.
        assertTrue(outputFile.getPath() + " must exist after run", outputFile.exists());
    }
}
