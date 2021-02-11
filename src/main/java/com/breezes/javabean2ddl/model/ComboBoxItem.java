package com.breezes.javabean2ddl.model;

import java.util.Objects;

/**
 * @author breezes_y@163.com
 * @date 2021/2/6 23:44
 * @description
 */
public class ComboBoxItem {

    protected String name;

    protected String value;

    public ComboBoxItem() {
    }

    public ComboBoxItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComboBoxItem that = (ComboBoxItem) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
