package com.nxc.docxfill.target;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;

/**
 * @author niXueChao
 * @date 2019/4/24 15:49.
 */
public class Sentence {

    private Target belongTarget;
    private Style style;
    private String text;

    public Sentence(String text, Target target) {
        if (text == null) {
            text = "";
        }
        this.text = text;
        this.belongTarget = target;
        this.style = new Style();
    }

    public Target and() {
        return this.build();
    }

    public Target build() {
        this.belongTarget.addSentence(this);
        return this.belongTarget;
    }


    /**
     * 加粗
     */
    public Sentence bold() {
        this.style.setBold(true);
        return this;
    }

    /**
     * 颜色,十六进制 如#8B0000
     */
    public Sentence color(String color) {
        this.style.setColor(color);
        return this;
    }

    /**
     * 双删除线
     */
    public Sentence doubleStrikethrough() {
        this.style.setDoubleStrikethrough(true);
        return this;
    }

    /**
     * 浮雕字体
     */
    public Sentence embossed() {
        this.style.setEmbossed(true);
        return this;
    }

    /**
     * 字体
     */
    public Sentence fontFamily(String fontFamily) {
        this.style.setFontFamily(fontFamily);
        return this;
    }

    /**
     * 字号
     */
    public Sentence fontSize(int fontSize) {
        this.style.setFontSize(fontSize);
        return this;
    }

    /**
     * 印迹（悬浮阴影）
     */
    public Sentence imprinted() {
        this.style.setImprinted(true);
        return this;
    }

    /**
     * 斜体
     */
    public Sentence italic() {
        this.style.setItalic(true);
        return this;
    }

    /**
     * 阴影
     */
    public Sentence shadow() {
        this.style.setShadow(true);
        return this;
    }

    /**
     * 单删除线
     */
    public Sentence strikeThrough() {
        this.style.setStrikeThrough(true);
        return this;
    }

    /**
     * 行距
     */
    public Sentence textPosition(int textPosition) {
        this.style.setTextPosition(textPosition);
        return this;
    }

    /**
     * 下划线
     */
    public Sentence underline(UnderlinePatterns underline) {
        this.style.setUnderline(underline);
        return this;
    }

    /**
     * 换行
     */
    public Sentence textBreak() {
        this.style.setTextBreak(true);
        return this;
    }

    /**
     * 文前Tab
     */
    public Sentence beforeTab() {
        this.style.setBeforeTab(true);
        return this;
    }

    /**
     * 文后Tab
     */
    public Sentence afterTab() {
        this.style.setAfterTab(true);
        return this;
    }

    /**
     * 文前回车
     */
    public Sentence beforeReturn() {
        this.style.setBeforeReturn(true);
        return this;
    }

    /**
     * 文后回车
     */
    public Sentence afterReturn() {
        this.style.setAfterReturn(true);
        return this;
    }

    /**
     * 设置为上标
     */
    public Sentence upTag() {
        this.style.setUpTag(true);
        return this;
    }

    /**
     * 设置为下标
     */
    public Sentence downTag() {
        this.style.setDownTag(true);
        return this;
    }


    public Style getStyle() {
        return this.style;
    }

    public String getText() {
        return this.text;
    }

}
