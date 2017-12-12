package com.shop.test;

import org.apache.log4j.Logger;

/**
 * @author ZGY
 */

public class Test {

    // 获取logger实例

    private static Logger logger=Logger.getLogger(String.valueOf(Test.class));


    public static void main(String[] args) {
        logger.info("普通Info信息");
        logger.debug("调试debug信息");
        logger.error("报错error信息");
        logger.warn("警告warn信息");
        logger.fatal("严重错误fatal信息");

        logger.error("报错信息", new IllegalArgumentException("非法参数"));
        int i=0;
        while(i<10000){
            logger.debug(" RollingFile 调试debug信息");
            logger.debug(" RollingFile 调试debug信息");
            logger.debug(" RollingFile 调试debug信息");
            logger.debug(" RollingFile 调试debug信息");
            logger.debug(" RollingFile 调试debug信息");
            i++;
        }
    }

}