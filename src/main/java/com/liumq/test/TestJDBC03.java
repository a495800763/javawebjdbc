package com.liumq.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC03 {

    @Test
    public void test() throws ClassNotFoundException, SQLException {
        //配置信息
        //useUnicode=true&characterEncoding=utf-8 解决中文乱码问题
        String url = "jdbc:mysql://localhost:3306/smbms?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";

        Connection connection = null;

        try {
            //1 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2 链接数据库,connection对象代表数据库
            connection = DriverManager.getConnection(url, username, password);


            //3 通知数据库开启事务，即设置不要自动commit
            connection.setAutoCommit(false);

            String sql = "update smbms_address set contact='柳梦琦' where id = 1";
            connection.prepareStatement(sql).executeUpdate();

            //制造错误
            //int i = 1 / 0;
            String sql2 = "update smbms_address set contact='刘曼' where id = 2";
            connection.prepareStatement(sql2).executeUpdate();

            connection.commit();
            System.out.println("success");
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }finally {
            connection.close();
        }


    }
}
