package org.example.generator.wusi.sql.condition;

/**
 * @author AdamSun
 * @date 2020/5/24 18:46
 */
public class From {
    private String tableName;
    /**
     * 自定义别名，如果不自定义，则使用默认的别名
     */
    private String nickName;

    public From(String tableName) {
        this(tableName, null);
    }

    public From(String tableName, String nickName) {
        this.tableName = tableName;
        this.nickName = nickName;
    }

    public static From create(String tableName) {
        return new From(tableName);
    }

    public static From create(String tableName, String nickName) {
        return new From(tableName, nickName);
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
}
