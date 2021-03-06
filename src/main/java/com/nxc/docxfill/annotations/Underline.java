package com.nxc.docxfill.annotations;

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author niXueChao
 * @date 2019/4/29 13:57.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Underline {
    UnderlinePatterns value() default UnderlinePatterns.NONE;
}
