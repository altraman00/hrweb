package com.mdl.zhaopin.utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.utils
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月15日 15:44
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class WordUtils {

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static String parseDoc2Html(File file) throws Exception {
        return parseDoc2Html(new FileInputStream(file));
    }

    public static String parseDoc2Html(File file, String charset) throws Exception {
        return parseDoc2Html(new FileInputStream(file), charset);
    }

    public static String parseDoc2Html(InputStream input) throws Exception {
        return parseDoc2Html(input, DEFAULT_CHARSET);
    }

    public static String parseDoc2Html(InputStream input, String charset) throws Exception {
        HWPFDocument wordDocument = new HWPFDocument(input);
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter converter = new WordToHtmlConverter(doc);
        converter.processDocument(wordDocument);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            DOMSource domSource = new DOMSource(converter.getDocument());
            StreamResult streamResult = new StreamResult(output);
            Transformer serializer = TransformerFactory.newInstance().newTransformer();
            // TODO 有乱码
            serializer.setOutputProperty(OutputKeys.ENCODING, charset);
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
        } finally {
            input.close();
            output.close();
        }

        return new String(output.toByteArray());
    }

}
