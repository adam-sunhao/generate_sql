package org.example.generator.wusi;

import org.example.generator.wusi.sql.SqlBuilder;
import org.example.generator.wusi.sql.condition.Condition;
import org.example.generator.wusi.sql.condition.From;
import org.example.generator.wusi.sql.condition.SelectCondition;
import org.example.generator.wusi.sql.condition.Where;
import org.example.generator.wusi.sql.enums.ConnectorEnum;
import org.example.generator.wusi.sql.enums.OperatorEnum;
import org.example.generator.wusi.sql.enums.ValueTypeEnum;
import org.junit.Test;

/**
 * @author AdamSun
 * @date 2020/5/25 20:08
 */

public class GenerateCodeTest {

    @Test
    public void generateWhereSql() {
        SqlBuilder builder = new SqlBuilder();
        SelectCondition[] selectConditions = new SelectCondition[]{
                SelectCondition.create("bicycle","b",new String[]{"id","mid"}),
                SelectCondition.create("commodity","c",new String[]{"id","name","pid","category"})
        };
        From[] froms = new From[]{From.create("bicycle", "b"), From.create("commodity", "c")};
        Where[] wheres = new Where[]{
                Where.create(Condition.create("bicycle", "b", "id"),
                        Condition.create("commodity", "c", "id"), OperatorEnum.EQ),
                Where.create(Condition.create("bicycle", "b", "id", "12345"),OperatorEnum.LIKE),
                Where.create(Condition.create("bicycle", "b", "id", "12345"),OperatorEnum.LIKE, ConnectorEnum.OR)
        };
        String result = builder.select(selectConditions).from(froms).where(wheres).build();
        System.out.println(result);
    }

}
