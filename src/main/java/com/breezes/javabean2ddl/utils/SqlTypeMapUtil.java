package com.breezes.javabean2ddl.utils;

import com.breezes.javabean2ddl.main.MainAction;
import com.breezes.javabean2ddl.setting.MainSetting;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuchengxin@xiaomalixing.com
 * @date 2021/2/10 23:15
 * @description 数据库类型和java类型映射工具类
 */
public class SqlTypeMapUtil {

    private static SqlTypeMapUtil sqlTypeMapUtil;

    private SqlTypeMapUtil() {
    }

    public static synchronized SqlTypeMapUtil getInstance() {
        if (sqlTypeMapUtil == null) {
            sqlTypeMapUtil = new SqlTypeMapUtil();
        }
        return sqlTypeMapUtil;
    }

    public ConcurrentHashMap<String, ConvertBean> convertMapInit() {
        MainSetting.MySettingProperties properties = MainSetting.getInstance().myProperties;
        ConcurrentHashMap<String, ConvertBean> convertMap = new ConcurrentHashMap<>();
        convertMap.put("int", new ConvertBean(properties.getIntType(), properties.getIntDefaultLength()));
        convertMap.put("Integer", new ConvertBean(properties.getIntType(), properties.getIntDefaultLength()));
        convertMap.put("long", new ConvertBean(properties.getLongType(), properties.getLongDefaultLength()));
        convertMap.put("Long", new ConvertBean(properties.getLongType(), properties.getLongDefaultLength()));
        convertMap.put("double", new ConvertBean(properties.getDoubleType(), properties.getDoubleDefaultLength()));
        convertMap.put("Double", new ConvertBean(properties.getDoubleType(), properties.getDoubleDefaultLength()));
        convertMap.put("float", new ConvertBean(properties.getFloatType(), properties.getFloatDefaultLength()));
        convertMap.put("Float", new ConvertBean(properties.getFloatType(), properties.getFloatDefaultLength()));
        convertMap.put("boolean", new ConvertBean(properties.getBooleanType(), properties.getBooleanDefaultLength()));
        convertMap.put("Boolean", new ConvertBean(properties.getBooleanType(), properties.getBooleanDefaultLength()));
        convertMap.put("Date", new ConvertBean(properties.getDateType(), properties.getDateDefaultLength()));
        convertMap.put("String", new ConvertBean(properties.getStringType(), properties.getStringDefaultLength()));
        convertMap.put("char", new ConvertBean(properties.getStringType(), properties.getStringDefaultLength()));
        convertMap.put("Character", new ConvertBean(properties.getStringType(), properties.getStringDefaultLength()));
        return convertMap;
    }

    public ConvertBean typeConvert(String javaType) {
        if (StringUtils.isBlank(javaType)) {
            return null;
        }
        return MainAction.convertMap.get(javaType);
    }

    public static class ConvertBean {
        private String sqlType;
        private String sqlTypeLength;

        public ConvertBean() {
        }

        public ConvertBean(String sqlType, String sqlTypeLength) {
            this.sqlType = sqlType;
            this.sqlTypeLength = sqlTypeLength;
        }

        public String getSqlType() {
            return sqlType;
        }

        public void setSqlType(String sqlType) {
            this.sqlType = sqlType;
        }

        public String getSqlTypeLength() {
            return sqlTypeLength;
        }

        public void setSqlTypeLength(String sqlTypeLength) {
            this.sqlTypeLength = sqlTypeLength;
        }
    }
}
