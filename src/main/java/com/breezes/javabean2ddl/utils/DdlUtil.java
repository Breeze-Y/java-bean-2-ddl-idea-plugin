package com.breezes.javabean2ddl.utils;

import com.breezes.javabean2ddl.DdlBuilder;
import com.breezes.javabean2ddl.model.Field;

import java.util.List;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/1/30 16:48
 * @description
 */
public class DdlUtil {

    public static String buildDdlScript(String tableName, List<Field> fieldList) {
        DdlBuilder builder = new DdlBuilder().create()
                .tableName(tableName)
                .LeftParenthesis()
                .wrap();

        for (Field field : fieldList) {
            builder.space().space().space().space().
                    addField(field.getTableColumn(), field.getType(), field.getPrimaryKey())
                    .wrap();
        }
        return builder.remove().remove()
                .wrap()
                .rightParenthesis()
                .remove()
                .end();
    }

}
