package org.example.generator.wusi.sql;

import org.example.generator.wusi.sql.condition.Condition;
import org.example.generator.wusi.sql.condition.From;
import org.example.generator.wusi.sql.condition.SelectCondition;
import org.example.generator.wusi.sql.condition.Where;
import org.example.generator.wusi.sql.enums.OperatorEnum;
import org.example.generator.wusi.sql.enums.ValueTypeEnum;
import org.example.generator.wusi.util.StringUtils;

import java.util.List;

/**
 * @author AdamSun
 * @date 2020/5/24 11:45
 */
public class SqlBuilder {

    private StringBuilder sqlBuilder = new StringBuilder();
    private boolean queryAll = false;
    private boolean isFirstWhere = true;

    public SqlBuilder select(SelectCondition... conditions) {
        sqlBuilder.append("SELECT  \r\n");
        for (int i = 0; i < conditions.length; i++) {
            SelectCondition condition = conditions[i];
            Table table = DataCache.getTable(condition.getTableName());
            String nickName = StringUtils.isBlank(condition.getNickName()) ? table.getNickName() : condition.getNickName();
            String[] columnName = condition.getColumnName();
            for (int j = 0; j < columnName.length; j++) {
                boolean isLast = ((i == (conditions.length - 1)) && (j == (columnName.length - 1)));
                sqlBuilder.append("    ").append(nickName).append(".").append(columnName[j])
                        .append(isLast ? " \r\n" : ", \r\n");
            }
        }
        return this;
    }

    public SqlBuilder selectAll() {
        queryAll = true;
        sqlBuilder.append("SELECT \r\n");
        return this;
    }

    public SqlBuilder from(String tableName) {
        if (queryAll) {
            List<Column> columnList = DataCache.getColumnList(tableName);
            int size = columnList.size();
            for (int i = 0; i < size; i++) {
                Column column = columnList.get(i);
                sqlBuilder.append("    ").append(column.getColumnName());
                if (i != (size - 1)) {
                    sqlBuilder.append(",    ");
                }
                sqlBuilder.append("\r\n");
            }
        }
        sqlBuilder.append("FROM \r\n")
                .append("    ").append(tableName);
        return this;
    }

    public SqlBuilder from(From... froms) {
        if (froms == null || froms.length == 0) {
            throw new RuntimeException(" FROM 后不允许为空！");
        }
        int length = froms.length;
        if (queryAll) {
            for (int i = 0; i < froms.length; i++) {
                From from = froms[i];
                Table table = DataCache.getTable(from.getTableName());
                String nickName = StringUtils.isBlank(from.getNickName()) ? table.getNickName() : from.getNickName();
                List<Column> columnList = DataCache.getColumnList(from.getTableName());
                int size = columnList.size();
                for (int j = 0; j < size; j++) {
                    Column column = columnList.get(j);
                    sqlBuilder.append("    ").append(nickName).append(".").append(column.getColumnName())
                            .append(j == (size - 1) ? "  \r\n" : ",  \r\n");
                }
            }
        }
        sqlBuilder.append("FROM \r\n");
        for (int i = 0; i < froms.length; i++) {
            From from = froms[i];
            Table table = DataCache.getTable(from.getTableName());
            String nickName = StringUtils.isBlank(from.getNickName()) ? table.getNickName() : from.getNickName();
            sqlBuilder.append("    ").append(from.getTableName()).append(" ").append(nickName)
                    .append(i == (length - 1) ? " \r\n" : ", \r\n");
        }
        return this;
    }

    /**
     * 默认全部使用and进行连接
     *
     * @param wheres
     * @return
     */
    public SqlBuilder where(Where... wheres) {
        sqlBuilder.append("WHERE \r\n");
        for (int i = 0; i < wheres.length; i++) {
            Where where = wheres[i];
            connect(where);
        }
        return this;
    }

    public SqlBuilder and(Where where) {
        return connect(where);
    }

    public SqlBuilder or(Where where) {
        return connect(where);
    }

    private SqlBuilder connect(Where where) {
        Condition left = where.getLeftTable();
        Condition right = where.getRightTable();
        Table leftTable = DataCache.getTable(left.getTableName());
        String leftNickName = StringUtils.isBlank(left.getNickName()) ? leftTable.getNickName() : left.getNickName();
        String operator = where.getOperator().getOperator();
        if (isFirstWhere) {
            isFirstWhere = false;
            sqlBuilder.append("    ");
        } else {
            sqlBuilder.append("    ").append(where.getConnector().getConnector()).append(" ");
        }
        //说明单边进行值判断
        if (right == null) {
            sqlBuilder.append(leftNickName).append(".").append(left.getColumnName())
                    .append(operator);
            //如果值的类型为空，或者不为varchar，则默认不加引号
            if (OperatorEnum.LIKE.equals(where.getOperator())) {
                sqlBuilder.append("'%").append(left.getColumnValue()).append("%'");
            } else if (ValueTypeEnum.VARCHAR.equals(left.getValueType())) {
                sqlBuilder.append("'").append(left.getColumnValue()).append("'");
            } else {
                sqlBuilder.append(left.getColumnValue());
            }
            sqlBuilder.append("\r\n");
        } else {
            Table rightTable = DataCache.getTable(right.getTableName());
            String rightNickName = StringUtils.isBlank(right.getNickName()) ? rightTable.getNickName() : right.getNickName();
            sqlBuilder.append(leftNickName).append(".").append(left.getColumnName())
                    .append(operator).append(rightNickName).append(".").append(right.getColumnName()).append("\r\n");
        }
        return this;
    }

    public String build() {
        return sqlBuilder.toString();
    }
}
