package org.example.generator.wusi.util;

import java.sql.*;
import java.util.Properties;

/**
 * @author AdamSun
 * @date 2020/5/24 9:28
 */
public class DBUtils {

    private static Properties properties = null;
    private static String url;
    private static String username;
    private static String password;
    private static String filePath = "generate_config.properties";
    private static String driverClass = "database.mysql.driverClass";

    static {
        properties = PropertiesUtils.load(filePath);
        String className = properties.getProperty(driverClass);
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        url = properties.getProperty("database.mysql.url");
        username = properties.getProperty("database.mysql.username");
        password = properties.getProperty("database.mysql.password");
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void close(Connection conn) {
        close(null, conn, null);
    }

    public static void close(PreparedStatement ps, Connection conn) {
        close(ps, conn, null);
    }

    public static void close(PreparedStatement ps, Connection conn, ResultSet rs) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
