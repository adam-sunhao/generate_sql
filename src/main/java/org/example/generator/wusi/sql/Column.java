package org.example.generator.wusi.sql;

/**
 * 数据库中表相关字段信息
 *
 * @author AdamSun
 * @date 2020/5/24 9:03
 */
public class Column {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 注释
     */
    private String columnComment;
    /**
     * 列类型
     * e.g: varchar、datetime....
     */
    private String columnType;

    public Column() {
    }

    public Column(String columnName, String columnComment, String columnType) {
        this.columnName = columnName;
        this.columnComment = columnComment;
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", columnType='" + columnType + '\'' +
                '}';
    }
}
