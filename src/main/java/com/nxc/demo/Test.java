package com.nxc.demo;

import com.nxc.docxfill.WordDocument;
import com.nxc.docxfill.target.Target;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author niXueChao
 * @date 2019/4/30 10:21.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Template template = new Template();
        template.setName("这是姓名");
        template.setDescription("这是说明");

        template.setRemark(Target.builder()
                .withText("第一部分").beforeTab().afterReturn()
                .and()
                .withText("第二部分").color("#FF0000").beforeTab().afterReturn()
                .and()
                .withText("第三部分").bold().beforeTab()
                .and().withText("第四部分").upTag().build());

        //可以通过输入流或是文件路径构造
//        WordDocument wordDocument=new WordDocument(template, "D:/aaa.docx");
        WordDocument wordDocument=new WordDocument(template, Test.class.getResourceAsStream("/aaa.docx"));
        wordDocument.write(new FileOutputStream(new File("D:/bbb.docx")));
    }
}
