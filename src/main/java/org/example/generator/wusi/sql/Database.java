package org.example.generator.wusi.sql;

import java.util.List;

/**
 * 数据库相关信息
 *
 * @author AdamSun
 * @date 2020/5/24 9:05
 */
public class Database {
    /**
     * 数据库名
     */
    private String databaseName;
    /**
     * 数据库下相关表
     */
    private List<Table> tableList;

    public Database() {
    }

    public Database(String databaseName, List<Table> tableList) {
        this.databaseName = databaseName;
        this.tableList = tableList;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    @Override
    public String toString() {
        return "Database{" +
                "databaseName='" + databaseName + '\'' +
                ", tableList=" + tableList +
                '}';
    }
}
