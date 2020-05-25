package org.example.generator.wusi.sql.enums;

/**
 * @author AdamSun
 * @date 2020/5/24 21:09
 */
public enum OperatorEnum {
    EQ(" = "), NQ(" <> "), LIKE(" LIKE "), GT(" > "), LT(" < "),
    LT_EQ(" <= "), GT_EQ(" >= "), IS_NULL(" IS NULL "), IS_NOT_NULL(" IS NOT NULL ");
    private String operator;

    OperatorEnum(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
