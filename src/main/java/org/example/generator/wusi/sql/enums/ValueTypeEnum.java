package org.example.generator.wusi.sql.enums;

/**
 * 整数类型：BIT、BOOL、TINY INT、SMALL INT、MEDIUM INT、 INT、 BIG INT
 * <p>
 * 浮点数类型：FLOAT、DOUBLE、DECIMAL
 * <p>
 * 字符串类型：CHAR、VARCHAR、TINY TEXT、TEXT、MEDIUM TEXT、LONGTEXT、TINY BLOB、BLOB、MEDIUM BLOB、LONG BLOB
 * <p>
 * 日期类型：Date、DateTime、TimeStamp、Time、Year
 * <p>
 * 其他数据类型：BINARY、VARBINARY、ENUM、SET、Geometry、Point、MultiPoint、LineString、MultiLineString、Polygon、GeometryCollection等
 *
 * @author AdamSun
 * @date 2020/5/25 20:25
 */
public enum ValueTypeEnum {
    CHAR("CHAR"),VARCHAR("VARCHAR"),TEXT("TEXT"),BLOB("BLOB"),
    BIT("BIT"),BOOL("BOOL"),INT("INT"),FLOAT("FLOAT"),DOUBLE("DOUBLE"),DECIMAL("DECIMAL"),
    DATE("Date"), DATETIME("DateTime"),TIMESTAMP("TimeStamp"),TIME("Time"),YEAR("Year");
    private String valueType;

    ValueTypeEnum(String valueType) {
        this.valueType = valueType;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
