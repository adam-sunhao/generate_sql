package org.example.generator.wusi.sql;

import org.example.generator.wusi.util.DBUtils;

import java.util.*;

/**
 * @author AdamSun
 * @date 2020/5/24 15:42
 */
public class DataCache {
    private static Map<String, Database> dbCache = new HashMap<>();
    private static Map<String, Table> tbCache = new HashMap<>();
    private static Properties properties;

    static {
        properties = DBUtils.getProperties();
        //构建需要的数据
        SqlQuery query = new SqlQuery();
        List<Table> tableList = query.queryTableList();
        for (Table table : tableList) {
            tbCache.put(table.getTableName(), table);
        }
    }

    /**
     * 根据数据库名获取数据库相关数据
     *
     * @param databaseName
     * @return
     */
    public static Database getDatabase(String databaseName) {
        return null;
    }

    /**
     * 获取数据库列表
     *
     * @return
     */
    public static Set<Database> getDatabase() {
        return null;
    }

    /**
     * 获取所有表的相关信息
     *
     * @return
     */
    public static Collection<Table> getTable() {
        return tbCache.values();
    }

    public static Table getTable(String tableName) {
        Table searchTable = tbCache.get(tableName);
        return searchTable;
    }

    /**
     * 获取指定表名中的指定列
     *
     * @param tableName
     * @param columnName
     * @return
     */
    public static Column getColumn(String tableName, String columnName) {
        if (columnName == null) {
            return null;
        }
        List<Column> columnList = getColumnList(tableName);
        for (Column column : columnList) {
            if (columnName.equals(column.getColumnName())) {
                return column;
            }
        }
        return null;
    }

    /**
     * 指定表名获取所有的列
     *
     * @param tableName
     * @return
     */
    public static List<Column> getColumnList(String tableName) {
        if (tableName == null) {
            return null;
        }
        Table searchTable = tbCache.get(tableName);
        if (searchTable == null) {
            return null;
        }
        List<Column> columnList = searchTable.getColumnList();
        //如果缓存中不存在，则进行查询
        if (columnList == null) {
            SqlQuery query = new SqlQuery();
            columnList = query.queryColumnList(tableName);
            if (columnList == null) {
                columnList = new ArrayList<>();
            }
            tbCache.get(tableName).setColumnList(columnList);
        }
        return columnList;
    }

    /**
     * @param comment 根据注释查询相关表
     * @return
     */
    public static List<Table> queryTableByComment(String comment) {
        List<Table> resultList = new ArrayList<>();
        Set<String> tableNames = tbCache.keySet();
        for (String tableName : tableNames) {
            Table table = tbCache.get(tableName);
            String tableComment = table.getTableComment();
            if (tableComment == null) {
                continue;
            }
            if (tableComment.indexOf(comment) != -1) {
                resultList.add(table);
            }
        }
        return resultList;
    }

    /**
     * 获取表中注释为指定值的字段
     *
     * @param tableName
     * @param columnComment
     * @return
     */
    public static List<Column> queryColumnByComment(String tableName, String columnComment) {
        List<Column> resultList = new ArrayList<>();

        List<Column> columnList = getColumnList(tableName);
        if (columnList != null) {
            for (Column column : columnList) {
                String comment = column.getColumnComment();
                if (comment != null && comment.indexOf(columnComment) != -1) {
                    resultList.add(column);
                }
            }
        }
        return resultList;
    }
}
