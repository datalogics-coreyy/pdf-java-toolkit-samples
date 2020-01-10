/*
 * Copyright 2015 Datalogics, Inc.
 */

package com.datalogics.pdf.samples.manipulation;

import com.adobe.internal.io.*;
import com.adobe.pdfjt.core.license.LicenseManager;
import com.adobe.pdfjt.pdf.document.PDFDocument;
import com.adobe.pdfjt.pdf.document.PDFOpenOptions;
import com.adobe.pdfjt.pdf.document.PDFSaveLinearOptions;
import com.adobe.pdfjt.pdf.document.PDFSaveOptions;
import com.adobe.pdfjt.services.sanitization.SanitizationOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public final class SaveLinearized {

    public static final String OUTPUT_PDF_PATH = "InteractivityRemoved.pdf";
    public static final String INPUT_PDF_PATH = "PDFJT-1273.pdf";

    /**
     * This is a utility class, and won't be instantiated.
     */
    private SaveLinearized() {}

    private static void printTime(String label) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(label + dateFormat.format(date));
    }

    /**
     * Main program.
     *
     * @param args command line arguments
     * @throws Exception a general exception was thrown
     */
    public static void main(final String... args) throws Exception {
        printTime("Start: ");
        if (args.length ==2) {
            InputStream ins = new FileInputStream(new File(args[0]));
            ByteReader br = new InputStreamByteReader(ins);
            PDFDocument doc = PDFDocument.newInstance(br, PDFOpenOptions.newInstance());
            final PDFSaveOptions saveOptions = PDFSaveLinearOptions.newInstance();
            final SanitizationOptions options = new SanitizationOptions();
            options.setSaveOptions(saveOptions);
            final RandomAccessFile outputPdfFile = new RandomAccessFile(new File(args[1]), "rw");
            ByteWriter writer = new RandomAccessFileByteWriter(outputPdfFile);
            doc.saveAndClose(writer, saveOptions);
        }

        printTime("End: ");
    }
}
