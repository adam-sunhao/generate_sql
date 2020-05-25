package org.example.generator.wusi.sql.condition;

import org.example.generator.wusi.sql.enums.ConnectorEnum;
import org.example.generator.wusi.sql.enums.OperatorEnum;

/**
 * @author AdamSun
 * @date 2020/5/24 18:46
 */
public class Where {
    private Condition leftTable;
    private Condition rightTable;
    private OperatorEnum operator;
    private ConnectorEnum connector = ConnectorEnum.AND;

    public Where(Condition leftTable, OperatorEnum operator) {
        this(leftTable, null, operator);
    }

    public Where(Condition leftTable, OperatorEnum operator, ConnectorEnum connector) {
        this.leftTable = leftTable;
        this.operator = operator;
        this.connector = connector;
    }

    public Where(Condition leftTable, Condition rightTable, OperatorEnum operator) {
        this.leftTable = leftTable;
        this.rightTable = rightTable;
        this.operator = operator;
    }

    public Where(Condition leftTable, Condition rightTable, OperatorEnum operator, ConnectorEnum connector) {
        this.leftTable = leftTable;
        this.rightTable = rightTable;
        this.connector = connector;
        this.operator = operator;
    }

    public static Where create(Condition leftTable, OperatorEnum operator) {
        return new Where(leftTable, operator);
    }

    public static Where create(Condition leftTable, OperatorEnum operator, ConnectorEnum connector) {
        return new Where(leftTable, operator, connector);
    }

    public static Where create(Condition leftTable, Condition rightTable, OperatorEnum operator) {
        return new Where(leftTable, rightTable, operator);
    }

    public static Where create(Condition leftTable, Condition rightTable, OperatorEnum operator, ConnectorEnum connector) {
        return new Where(leftTable, rightTable, operator, connector);
    }

    public Condition getLeftTable() {
        return leftTable;
    }

    public void setLeftTable(Condition leftTable) {
        this.leftTable = leftTable;
    }

    public Condition getRightTable() {
        return rightTable;
    }

    public void setRightTable(Condition rightTable) {
        this.rightTable = rightTable;
    }

    public OperatorEnum getOperator() {
        return operator;
    }

    public void setOperator(OperatorEnum operator) {
        this.operator = operator;
    }

    public ConnectorEnum getConnector() {
        return connector;
    }

    public void setConnector(ConnectorEnum connector) {
        this.connector = connector;
    }
}
