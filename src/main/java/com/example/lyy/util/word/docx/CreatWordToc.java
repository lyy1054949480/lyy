package com.example.lyy.util.word.docx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.xwpf.usermodel.TOC;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatWordToc extends XWPFDocument{

    public XWPFDocument doc;
    @Override
    public void createTOC() {
        CTSdtBlock block = doc.getDocument().getBody().addNewSdt();
        TOC toc = new TOC(block);

        /*当前位置调用添加正文的方法，需要传参XWPFDocumen对象*/
//        writeAllNews(doc);

        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph par : paragraphs) {
            String parStyle = par.getStyle();
            if (parStyle != null) {


                //获取书签，书签的对应关系很重要，关系到目录能否正常跳转
                List<CTBookmark> bookmarkList = par.getCTP().getBookmarkStartList();
                try {
                    int level = Integer.parseInt(parStyle);

                    //添加标题
                    if (!CollectionUtils.isEmpty(bookmarkList)){
                        toc.addRow(level, par.getText(), 1, bookmarkList.get(0).getName());
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 生成目录
     * @param doc
     */
    public static void createTOC(XWPFDocument doc) {
        CTSdtBlock block = doc.getDocument().getBody().addNewSdt();
        TOC toc = new TOC(block);

        /*当前位置调用添加正文的方法，需要传参XWPFDocumen对象*/
       // writeAllNews(doc);

        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph par : paragraphs) {
            List<XWPFRun> runs = par.getRuns();
            for (XWPFRun run : runs) {

            }
            String parStyle = par.getStyle();
            if (parStyle != null) {

                //获取书签，书签的对应关系很重要，关系到目录能否正常跳转
                List<CTBookmark> bookmarkList = par.getCTP().getBookmarkStartList();
                try {
                    int level = Integer.parseInt(parStyle);

                    //添加标题
                    toc.addRow(level, par.getText(), 1, null);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
