package com.breezes.javabean2ddl.utils;

import com.breezes.javabean2ddl.model.Field;
import com.breezes.javabean2ddl.service.DdlBuilder;

import java.util.List;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 16:48
 * @description
 */
public class DdlFormatUtil {

    public static String buildDdlScript(String tableName, List<Field> fieldList) {
        DdlBuilder builder = new DdlBuilder().create()
                .tableName(tableName)
                .LeftParenthesis()
                .wrap();


        int maxFieldStringLength = 0;
        int maxFieldSqlTypeStringLength = 0;
        for (Field field : fieldList) {
            if (maxFieldStringLength <= field.getTableColumn().length()) {
                maxFieldStringLength = field.getTableColumn().length();
            }
            if (maxFieldSqlTypeStringLength <= field.getSqlType().length()) {
                maxFieldSqlTypeStringLength = field.getSqlType().length();
            }
        }
        maxFieldStringLength++;
        maxFieldSqlTypeStringLength++;

        for (Field field : fieldList) {
            String tableColumn = field.getTableColumn();
            builder.space(4)
                    .addColumn(String.format("%-" + maxFieldStringLength + "s", tableColumn))
                    .addType(String.format("%-" + maxFieldSqlTypeStringLength + "s", field.getSqlType()))
                    .isPrimaryKey(field.isPrimaryKey())
                    .space()
                    .addComment(field.getCommend())
                    .addComma()
                    .wrap();
        }

        return builder.remove(2)
                .wrap()
                .rightParenthesis()
                .remove()
                .end();
    }

}
