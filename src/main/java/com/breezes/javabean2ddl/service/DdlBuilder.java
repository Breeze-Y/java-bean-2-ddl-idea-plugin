package com.breezes.javabean2ddl.service;

/**
 * @author breezes_y@163.com
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
        return this;
    }

    public DdlBuilder addField(String field, String type) {
        return addField(field, type, false);
    }

    public DdlBuilder addField(String field, String type, boolean isPrimaryKey) {
        ddl.append(field).append(type);
        if (isPrimaryKey) {
            ddl.append(" AUTO_INCREMENT PRIMARY KEY");
        } else {
            ddl.append("  NULL");
        }
        ddl.append(",");
        return this;
    }

    public DdlBuilder addColumn(String field) {
        ddl.append(field);
        return this;
    }

    public DdlBuilder addType(String type) {
        ddl.append(type);
        return this;
    }

    public DdlBuilder isPrimaryKey(boolean isPrimaryKey) {
        if (isPrimaryKey) {
            ddl.append("AUTO_INCREMENT PRIMARY KEY");
        } else {
            ddl.append(" NULL");
        }
        return this;
    }

    public DdlBuilder addComma() {
        ddl.append(",");
        return this;
    }

    public DdlBuilder space() {
        ddl.append(" ");
        return this;
    }

    public DdlBuilder space(int size) {
        if (size <= 0) {
            size = 1;
        }
        for (int i = 0; i < size; i++) {
            space();
        }
        return this;
    }

    public DdlBuilder addComment(String commend) {
        ddl.append("COMMENT ").append("'").append(commend).append("'");
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

    public DdlBuilder remove(int size) {
        ddl.delete(ddl.length() - size, ddl.length());
        return this;
    }

    public String end() {
        return ddl.append(";").toString();
    }

}
