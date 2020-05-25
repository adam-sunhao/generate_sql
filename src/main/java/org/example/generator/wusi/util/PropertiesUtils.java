package org.example.generator.wusi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author AdamSun
 * @date 2020/5/24 10:35
 */
public class PropertiesUtils {

    /**
     * 从指定路径加载配置文件
     *
     * @param filePath
     * @return
     */
    public static Properties load(String filePath) {
        Properties prop = new Properties();
        //获取输入流
        InputStream in = ClassLoader.getSystemResourceAsStream(filePath);
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        StringUtils.format(prop);
        return prop;
    }
}
