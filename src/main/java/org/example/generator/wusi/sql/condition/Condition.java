package org.example.generator.wusi.sql.condition;

import org.example.generator.wusi.sql.enums.ValueTypeEnum;

/**
 * where 条件、order by条件....
 *
 * @author AdamSun
 * @date 2020/5/24 21:30
 */
public class Condition {
    private String tableName;
    private String nickName;
    private String columnName;
    private String columnValue;
    private ValueTypeEnum valueType = ValueTypeEnum.VARCHAR;

    public Condition(String tableName, String columnName) {
        this(tableName, null, columnName);
    }

    public Condition(String tableName, String nickName, String columnName) {
        this(tableName, nickName, columnName, null);
    }

    public Condition(String tableName, String nickName, String columnName, String columnValue) {
        this.tableName = tableName;
        this.nickName = nickName;
        this.columnName = columnName;
        this.columnValue = columnValue;
    }

    public Condition(String tableName, String nickName, String columnName, String columnValue, ValueTypeEnum valueType) {
        this.tableName = tableName;
        this.nickName = nickName;
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.valueType = valueType;
    }

    public static Condition create(String tableName, String columnName) {
        return new Condition(tableName, columnName);
    }

    public static Condition create(String tableName, String nickName, String columnName) {
        return new Condition(tableName, nickName, columnName);
    }

    public static Condition create(String tableName, String nickName, String columnName, String columnValue) {
        return new Condition(tableName, nickName, columnName, columnValue);
    }

    public static Condition create(String tableName, String nickName, String columnName, String columnValue, ValueTypeEnum valueType) {
        return new Condition(tableName, nickName, columnName, columnValue, valueType);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public ValueTypeEnum getValueType() {
        return valueType;
    }

    public void setValueType(ValueTypeEnum valueType) {
        this.valueType = valueType;
    }
}
