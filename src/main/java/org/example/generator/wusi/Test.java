package org.example.generator.wusi;

import org.example.generator.wusi.sql.SqlBuilder;
import org.example.generator.wusi.sql.condition.Condition;
import org.example.generator.wusi.sql.condition.From;
import org.example.generator.wusi.sql.condition.SelectCondition;
import org.example.generator.wusi.sql.condition.Where;
import org.example.generator.wusi.sql.enums.OperatorEnum;

/**
 * @author AdamSun
 * @date 2020/5/24 17:29
 */
public class Test {

    public static void main(String[] args) {
        //目前只能自己约束了

        /*
         * 想查询的字段，需要指定表及字段名
         * 从哪些表中进行查询
         * 判断条件、哪些表中的哪些字段相等
         * 或者某个表中的值为某个值
         */

        SqlBuilder builder = new SqlBuilder();
        SelectCondition[] selectConditions = new SelectCondition[]{
                SelectCondition.create("bicycle","b",new String[]{"id","mid"}),
                SelectCondition.create("commodity","c",new String[]{"id","name","pid","category"})
        };
        From[] froms = new From[]{From.create("bicycle", "b"), From.create("commodity", "c")};
        Where[] wheres = new Where[]{
               Where.create(Condition.create("bicycle", "b", "id"),
                       Condition.create("commodity", "c", "id"), OperatorEnum.EQ),
                Where.create(Condition.create("bicycle", "b", "id"),
                        Condition.create("commodity", "c", "pid"), OperatorEnum.EQ)
        };
        String result = builder.select(selectConditions).from(froms).where(wheres).build();
        System.out.println(result);
        result = GenerateCode.sql2Java("sql");
        System.out.println(result);
    }
}
