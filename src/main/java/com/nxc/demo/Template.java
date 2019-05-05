package com.nxc.demo;

import com.nxc.docxfill.annotations.*;
import com.nxc.docxfill.target.Target;

/**
 * @author niXueChao
 * @date 2019/4/25 9:49.
 */
public class Template {

    @StrikeThrough
    @FontFamily("黑体")
    @FontSize(35)
    @TextBreak
    private String name;

    @Color("#FF0000")
    @FontSize(30)
    @TextBreak
    private String description;

    private Target remark;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Target getRemark() {
        return remark;
    }

    public void setRemark(Target remark) {
        this.remark = remark;
    }
}
