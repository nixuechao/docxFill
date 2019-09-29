package com.nxc.docxfill;

import com.nxc.docxfill.annotations.*;
import com.nxc.docxfill.target.Sentence;
import com.nxc.docxfill.target.Style;
import com.nxc.docxfill.target.Target;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author niXueChao
 * @date 2019/4/29 16:27.
 */
public class WordDocument {
    private XWPFDocument xwpfDocument;

    private String label ="$";

    public WordDocument(Object fillObject,String templatePath) {
        try {
            this.xwpfDocument=generateWord(fillObject,new FileInputStream(templatePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WordDocument(Object fillObject,InputStream templateInputStream) {
        try {
            this.xwpfDocument=generateWord(fillObject, templateInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(OutputStream outputStream){
        try {
            xwpfDocument.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private XWPFDocument generateWord(Object object, InputStream inputStream)throws Exception {
        XWPFDocument doc = new XWPFDocument(inputStream);
        if (object != null && object.getClass().getDeclaredFields().length > 0) {

            // 处理段落
            List<XWPFParagraph> paragraphList = doc.getParagraphs();
            processParagraphs(paragraphList, object);

            // 处理表格
            Iterator<XWPFTable> it = doc.getTablesIterator();
            while (it.hasNext()) {
                XWPFTable table = it.next();
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        List<XWPFParagraph> paragraphListTable = cell
                                .getParagraphs();
                        processParagraphs(paragraphListTable, object);
                    }
                }
            }
        }
        return doc;
    }

    private void processParagraphs(List<XWPFParagraph> paragraphList, Object obj) throws Exception {
        if (paragraphList != null && paragraphList.size() > 0) {
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> oldRuns = new ArrayList<>(paragraph.getRuns());
                for (XWPFRun oldRun : oldRuns) {
                    String oldText = oldRun.getText(0);
                    if (oldText == null) {
                        continue;
                    }
                    CTRPr oldRpr = oldRun.getCTR().getRPr();
                    while (oldText.contains(label)) {
                        int first = oldText.indexOf(label);
                        int next = oldText.indexOf(label, first + 1);
                        String tar = oldText.substring(first + 1, next);

                        for (Field field : obj.getClass().getDeclaredFields()) {
                            String fieldName = field.getName();
                            if ("this$0".equals(fieldName)) {
                                //貌似非静态内部类会有一个指向外部类的引用字段
                                continue;
                            }
                            if (!tar.equals(fieldName)) {
                                continue;
                            }
                            field.setAccessible(true);
                            String preOldText = oldText.substring(0, first);
                            String leftOldText = oldText.substring(next + 1);
                            //prePart
                            oldRun.setText(preOldText, 0);
                            //newInsert
                            int i = paragraph.getRuns().indexOf(oldRun);
                            if (field.getType() == Target.class) {
                                Target targetObj = (Target) field.get(obj);
                                if (targetObj == null) {
                                    continue;
                                }
                                List<Sentence> sentences = targetObj.getSentences();
                                for (Sentence sentence : sentences) {
                                    XWPFRun newRun = paragraph.insertNewRun(++i);
                                    Style style = sentence.getStyle();
                                    beforeTestStyle(style, null, newRun);
                                    newRun.setText(sentence.getText());
                                    newRun.getCTR().setRPr(oldRpr);
                                    afterTextStyle(style, null, newRun);
                                }
                            } else {
                                XWPFRun newRun = paragraph.insertNewRun(++i);
                                String value = (String) field.get(obj);
                                beforeTestStyle(null, field, newRun);
                                newRun.setText(value, 0);
                                newRun.getCTR().setRPr(oldRpr);
                                afterTextStyle(null, field, newRun);
                            }
                            //leftPart
                            XWPFRun newRun = paragraph.insertNewRun(++i);
                            newRun.setText(leftOldText, 0);
                            newRun.getCTR().setRPr(oldRpr);
                            oldText = leftOldText;
                            oldRun = newRun;
                            break;
                        }
                    }

                }
            }
        }
    }

    private void afterTextStyle(Style style, Field field, XWPFRun run) {
        if (style != null) {
            if (style.getAfterReturn() != null && style.getAfterReturn()) {
                run.addBreak();
            }
            if (style.getAfterTab() != null && style.getAfterTab()) {
                run.addTab();
            }
            if (style.getBold() != null) {
                run.setBold(style.getBold());
            }
            if (style.getColor() != null) {
                run.setColor(style.getColor().replace("#", ""));
            }
            if (style.getDoubleStrikethrough() != null) {
                run.setDoubleStrikethrough(style.getDoubleStrikethrough());
            }
            if (style.getEmbossed() != null) {
                run.setEmbossed(style.getEmbossed());
            }
            if (style.getFontFamily() != null) {
                run.setFontFamily(style.getFontFamily());
            }
            if (style.getFontSize() != null) {
                run.setFontSize(style.getFontSize());
            }
            if (style.getImprinted() != null) {
                run.setImprinted(style.getImprinted());
            }
            if (style.getItalic() != null) {
                run.setItalic(style.getItalic());
            }
            if (style.getShadow() != null) {
                run.setShadow(style.getShadow());
            }
            if (style.getStrikeThrough() != null) {
                run.setStrikeThrough(style.getStrikeThrough());
            }
            if (style.getTextBreak() != null && style.getTextBreak()) {
                run.addBreak();
            }
            if (style.getUpTag() != null && style.getUpTag()) {
                run.setSubscript(VerticalAlign.SUPERSCRIPT);
            }
            if (style.getDownTag() != null && style.getDownTag()) {
                run.setSubscript(VerticalAlign.SUBSCRIPT);
            }
        } else {
            if (field.isAnnotationPresent(AfterReturn.class)) {
                AfterReturn afterReturn = field.getAnnotation(AfterReturn.class);
                if (afterReturn.value()) {
                    run.addBreak();
                }
            }
            if (field.isAnnotationPresent(AfterTab.class)) {
                AfterTab annotation = field.getAnnotation(AfterTab.class);
                if (annotation.value()) {
                    run.addTab();
                }
            }
            if (field.isAnnotationPresent(Bold.class)) {
                boolean value = field.getAnnotation(Bold.class).value();
                run.setBold(value);
            }
            if (field.isAnnotationPresent(Color.class)) {
                String color = field.getAnnotation(Color.class).value();
                color = color.replace("#", "");
                run.setColor(color);
            }
            if (field.isAnnotationPresent(DoubleStrikethrough.class)) {
                boolean value = field.getAnnotation(DoubleStrikethrough.class).value();
                run.setDoubleStrikethrough(value);
            }
            if (field.isAnnotationPresent(Embossed.class)) {
                boolean value = field.getAnnotation(Embossed.class).value();
                run.setEmbossed(value);
            }
            if (field.isAnnotationPresent(FontFamily.class)) {
                String value = field.getAnnotation(FontFamily.class).value();
                run.setFontFamily(value);
            }
            if (field.isAnnotationPresent(FontSize.class)) {
                int value = field.getAnnotation(FontSize.class).value();
                run.setFontSize(value);
            }
            if (field.isAnnotationPresent(Imprinted.class)) {
                boolean value = field.getAnnotation(Imprinted.class).value();
                run.setImprinted(value);
            }
            if (field.isAnnotationPresent(Italic.class)) {
                boolean value = field.getAnnotation(Italic.class).value();
                run.setItalic(value);
            }
            if (field.isAnnotationPresent(Shadow.class)) {
                boolean value = field.getAnnotation(Shadow.class).value();
                run.setShadow(value);
            }
            if (field.isAnnotationPresent(StrikeThrough.class)) {
                boolean value = field.getAnnotation(StrikeThrough.class).value();
                run.setStrikeThrough(value);
            }
            if (field.isAnnotationPresent(TextBreak.class)) {
                if (field.getAnnotation(TextBreak.class).value()) {
                    run.addBreak();
                }
            }
            if (field.isAnnotationPresent(TextPosition.class)) {
                int value = field.getAnnotation(TextPosition.class).value();
                run.setTextPosition(value);
            }
            if (field.isAnnotationPresent(Underline.class)) {
                UnderlinePatterns value = field.getAnnotation(Underline.class).value();
                run.setUnderline(value);
            }
            if (field.isAnnotationPresent(UpTag.class)) {
                if (field.getAnnotation(UpTag.class).value()) {
                    run.setSubscript(VerticalAlign.SUPERSCRIPT);
                }
            }
            if (field.isAnnotationPresent(DownTag.class)) {
                if (field.getAnnotation(DownTag.class).value()) {
                    run.setSubscript(VerticalAlign.SUBSCRIPT);
                }
            }
        }
    }

    private void beforeTestStyle(Style style, Field field, XWPFRun run) {
        if (style != null) {
            if (style.getBeforeTab() != null && style.getBeforeTab()) {
                run.addTab();
            }
            if (style.getBeforeReturn() != null && style.getBeforeReturn()) {
                run.addBreak();
            }
        } else {
            if (field.isAnnotationPresent(BeforeTab.class)) {
                if (field.getAnnotation(BeforeTab.class).value()) {
                    run.addTab();
                }
            }
            if (field.isAnnotationPresent(BeforeReturn.class)) {
                if (field.getAnnotation(BeforeReturn.class).value()) {
                    run.addBreak();
                }
            }
        }
    }

}
