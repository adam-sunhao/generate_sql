package org.example.generator.wusi.sql.condition;

/**
 * @author AdamSun
 * @date 2020/5/24 22:01
 */
public class SelectCondition {
    private String tableName;
    private String nickName;
    private String[] columnName;

    public SelectCondition(String tableName, String[] columnName) {
        this(tableName, null, columnName);
    }

    public SelectCondition(String tableName, String nickName, String[] columnName) {
        this.tableName = tableName;
        this.nickName = nickName;
        this.columnName = columnName;
    }

    public static SelectCondition create(String tableName, String[] columnName) {
        return new SelectCondition(tableName, columnName);
    }

    public static SelectCondition create(String tableName, String nickName, String[] columnName) {
        return new SelectCondition(tableName, nickName, columnName);
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

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

}
