package org.example.generator.wusi.sql.enums;

/**
 * @author AdamSun
 * @date 2020/5/24 21:02
 */
public enum ConnectorEnum {
    AND("AND"), OR("OR");

    private String connector;

    ConnectorEnum(String connector) {
        this.connector = connector;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }
}
