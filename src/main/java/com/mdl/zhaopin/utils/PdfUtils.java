package com.mdl.zhaopin.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.utils
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月15日 15:42
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class PdfUtils {

    public static String parsePdf2Text(InputStream input) throws Exception {
        PDDocument doc = PDDocument.load(input);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(output);
        try {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.writeText(doc, writer);
        } finally {
            doc.close();
            input.close();
            output.close();
            writer.close();
        }
        return new String(output.toByteArray());
    }

}
