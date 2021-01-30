package com.breezes.javabean2ddl;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 16:49
 * @description
 */
public class DdlBuilder {

    private StringBuffer ddl = new StringBuffer("");

    public DdlBuilder create() {
        ddl.append("CREATE");
        return this.space();
    }

    public DdlBuilder tableName(String tableName) {
        ddl.append("TABLE").append(" ").append(tableName);
        return this.wrap();
    }

    public DdlBuilder LeftParenthesis() {
        ddl.append("(");
        return this.space();
    }

    public DdlBuilder rightParenthesis() {
        ddl.append(")");
        return this.space();
    }

    public DdlBuilder addField(String field, String type) {
        return addField(field, type, false);
    }

    public DdlBuilder addField(String field, String type, boolean isPrimaryKey) {
        ddl.append(field).append("   ").append(type);
        if (isPrimaryKey) {
            ddl.append(" AUTO_INCREMENT PRIMARY KEY");
        } else {
            ddl.append("  NULL");
        }
        ddl.append(",");
        return this;
    }

    public DdlBuilder space() {
        ddl.append(" ");
        return this;
    }

    public DdlBuilder wrap() {
        ddl.append("\n");
        return this;
    }

    public DdlBuilder remove() {
        ddl.deleteCharAt(ddl.length() - 1);
        return this;
    }

    public String end() {
        return ddl.append(";").toString();
    }

}
