package org.example.generator.wusi.sql;

import java.util.List;

/**
 * 数据库中表相关信息
 *
 * @author AdamSun
 * @date 2020/5/24 9:02
 */
public class Table {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表注释
     */
    private String tableComment;
    /**
     * 表别名
     */
    private String nickName;
    /**
     * 表中相关字段
     */
    private List<Column> columnList;

    public Table() {
    }

    public Table(String tableName, String tableComment, List<Column> columnList) {
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.columnList = columnList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public Table(String tableName, String tableComment, String nickName, List<Column> columnList) {
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.nickName = nickName;
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", nickName='" + nickName + '\'' +
                ", columnList=" + columnList +
                '}';
    }
}
