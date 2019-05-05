package com.nxc.docxfill.target;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;

/**
 * @author niXueChao
 * @date 2019/4/24 15:54.
 */
public class Style {
    /**
     * 加粗
     */
    private Boolean bold;
    /**
     * 颜色,十六进制 如#8B0000
     */
    private String color;
    /**
     * 双删除线
     */
    private Boolean doubleStrikethrough;
    /**
     * 浮雕字体
     */
    private Boolean embossed;
    /**
     * 字体
     */
    private String fontFamily;
    /**
     * 字号
     */
    private Integer fontSize;
    /**
     * 印迹（悬浮阴影）
     */
    private Boolean imprinted;
    /**
     * 斜体
     */
    private Boolean italic;
    /**
     * 阴影
     */
    private Boolean shadow;
    /**
     * 单删除线
     */
    private Boolean strikeThrough;
    /**
     * 行距
     */
    private Integer textPosition;
    /**
     * 下划线
     */
    private UnderlinePatterns underline;
    /**
     * 换行
     */
    private Boolean textBreak;
    /**
     * 文前Tab
     */
    private Boolean beforeTab;
    /**
     * 文后Tab
     */
    private Boolean afterTab;
    /**
     * 文前回车
     */
    private Boolean beforeReturn;
    /**
     * 文后回车
     */
    private Boolean afterReturn;

    /**
     * 把文字设置为上标
     */
    private Boolean upTag;

    /**
     * 把文字设置为下标
     */
    private Boolean downTag;




    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getDoubleStrikethrough() {
        return doubleStrikethrough;
    }

    public void setDoubleStrikethrough(Boolean doubleStrikethrough) {
        this.doubleStrikethrough = doubleStrikethrough;
    }

    public Boolean getEmbossed() {
        return embossed;
    }

    public void setEmbossed(Boolean embossed) {
        this.embossed = embossed;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Boolean getImprinted() {
        return imprinted;
    }

    public void setImprinted(Boolean imprinted) {
        this.imprinted = imprinted;
    }

    public Boolean getItalic() {
        return italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    public Boolean getShadow() {
        return shadow;
    }

    public void setShadow(Boolean shadow) {
        this.shadow = shadow;
    }

    public Boolean getStrikeThrough() {
        return strikeThrough;
    }

    public void setStrikeThrough(Boolean strikeThrough) {
        this.strikeThrough = strikeThrough;
    }

    public Integer getTextPosition() {
        return textPosition;
    }

    public void setTextPosition(Integer textPosition) {
        this.textPosition = textPosition;
    }

    public UnderlinePatterns getUnderline() {
        return underline;
    }

    public void setUnderline(UnderlinePatterns underline) {
        this.underline = underline;
    }

    public Boolean getTextBreak() {
        return textBreak;
    }

    public void setTextBreak(Boolean textBreak) {
        this.textBreak = textBreak;
    }

    public Boolean getBeforeTab() {
        return beforeTab;
    }

    public void setBeforeTab(Boolean beforeTab) {
        this.beforeTab = beforeTab;
    }

    public Boolean getAfterTab() {
        return afterTab;
    }

    public void setAfterTab(Boolean afterTab) {
        this.afterTab = afterTab;
    }

    public Boolean getBeforeReturn() {
        return beforeReturn;
    }

    public void setBeforeReturn(Boolean beforeReturn) {
        this.beforeReturn = beforeReturn;
    }

    public Boolean getAfterReturn() {
        return afterReturn;
    }

    public void setAfterReturn(Boolean afterReturn) {
        this.afterReturn = afterReturn;
    }

    public Boolean getUpTag() {
        return upTag;
    }

    public void setUpTag(Boolean upTag) {
        this.upTag = upTag;
    }

    public Boolean getDownTag() {
        return downTag;
    }

    public void setDownTag(Boolean downTag) {
        this.downTag = downTag;
    }
}
