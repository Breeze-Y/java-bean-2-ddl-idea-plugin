package com.breezes.javabean2ddl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author breezes_y@163.com
 * @date 2021/2/5 14:03
 * @description
 */
public class BaiduTranslationResponse {

    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("error_msg")
    private String errorMsg;
    private String from;
    private String to;

    @JsonProperty("trans_result")
    private List<TransResult> transResult;

    public static class TransResult {
        private String src;
        private String dst;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<TransResult> getTransResult() {
        return transResult;
    }

    public void setTransResult(List<TransResult> transResult) {
        this.transResult = transResult;
    }

}
