package org.example.generator.wusi.sql;

import org.example.generator.wusi.util.DBUtils;
import org.example.generator.wusi.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author AdamSun
 * @date 2020/5/24 16:33
 */
public class SqlQuery {

    public List<Table> queryTableList() {
        Connection conn = null;
        Properties prop = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<String> existsNickName = new HashSet<>();
        List<Table> tableList = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            prop = DBUtils.getProperties();
            String sql = prop.getProperty("database.mysql.queryTable");
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Table table = new Table();
                String tableName = rs.getString("table_name");
                table.setTableName(tableName);
                table.setTableComment(rs.getString("table_comment"));

                String nickName = getNickName(tableName);
                while (true) {
                    if (!existsNickName.contains(nickName)) {
                        existsNickName.add(nickName);
                        table.setNickName(nickName);
                        break;
                    }
                    nickName = getRanNickName(nickName);
                }
                tableList.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps, conn, rs);
        }
        return tableList;
    }

    public List<Column> queryColumnList(String tableName) {
        Connection conn = null;
        Properties prop = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Column> columnList = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            prop = DBUtils.getProperties();
            String sql = prop.getProperty("database.mysql.queryColumn");
            ps = conn.prepareStatement(StringUtils.format(sql, tableName));
            rs = ps.executeQuery();
            while (rs.next()) {
                Column column = new Column();
                column.setColumnName(rs.getString("COLUMN_NAME"));
                column.setColumnType(rs.getString("COLUMN_TYPE"));
                column.setColumnComment(rs.getString("COLUMN_COMMENT"));
                columnList.add(column);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps, conn, rs);
        }
        return columnList;
    }

    /**
     * 通过表名获取nickName
     *
     * @param tableName
     * @return
     */
    private String getNickName(String tableName) {
        if (tableName.contains("_")) {
            int end = tableName.lastIndexOf("_");
            return tableName.substring(end + 1);
        }
        if (tableName.length() <= 4) {
            return tableName;
        }
        return tableName.substring(tableName.length() - 4);
    }

    /**
     * 别名重复，获取随机别名
     *
     * @param nickName
     * @return
     */
    private String getRanNickName(String nickName) {
        //小写字母97-122
        int index = (int) (Math.random() * 26) + 97;
        return (char) index + nickName;
    }
}
