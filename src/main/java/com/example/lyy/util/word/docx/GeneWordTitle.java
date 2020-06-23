package com.example.lyy.util.word.docx;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

import java.io.*;
import java.util.List;

public class GeneWordTitle {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        File file = new File("C:/Users/Administrator/Desktop/pic.doc");
        FileInputStream fileInputStream = new FileInputStream(file);
        XWPFDocument doc = new XWPFDocument(fileInputStream);
        //doc.createTOC();
        generateTOC(doc);
//        CreatWordToc creatWordToc = new CreatWordToc(doc);
//        creatWordToc.createTOC();
//        CreatWordToc.createTOC(doc);
        OutputStream out = new FileOutputStream(file);
        doc.write(out);
        out.close();
    }

    public static void generateTOC(XWPFDocument document) throws InvalidFormatException, FileNotFoundException, IOException {
        String findText = "toc";
        String replaceText = "";
        for (XWPFParagraph p : document.getParagraphs()) {

            for (XWPFRun r : p.getRuns()) {
                int pos = r.getTextPosition();
                String text = r.getText(pos);
                System.out.println(text);
                if (text != null && text.contains(findText)) {
                    text = text.replace(findText, replaceText);
                    r.setText(text, 0);
                    addField(p, "TOC \\o \"1-3\" \\h \\z \\u");
//                    addField(p, "TOC \\h");
                    break;
                }
            }
        }
    }

    private static void addField(XWPFParagraph paragraph, String fieldName) {
        CTSimpleField ctSimpleField = paragraph.getCTP().addNewFldSimple();
        ctSimpleField.setInstr(fieldName);
        ctSimpleField.setDirty(STOnOff.TRUE);
        ctSimpleField.addNewR().addNewT().setStringValue("<<fieldName>>");
    }
}