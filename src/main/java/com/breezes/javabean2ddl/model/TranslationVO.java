package com.breezes.javabean2ddl.model;

/**
 * @author breezes_y@163.com
 * @date 2021/2/5 11:58
 * @description
 */
public class TranslationVO {

    private String src = "";

    private String dst = "";

    public TranslationVO() {
    }

    public TranslationVO(String src, String dst) {
        this.src = src;
        this.dst = dst;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }
}
