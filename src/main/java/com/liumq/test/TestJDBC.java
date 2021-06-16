package com.liumq.test;

import java.sql.*;

/**
 * jdbc六部曲
 */
public class TestJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        //useUnicode=true&characterEncoding=utf-8 解决中文乱码问题
        String url = "jdbc:mysql://localhost:3306/smbms?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "123456";
        //1 加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2 链接数据库,connection对象代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        //3 创建向数据库发送sql 的对象 statement :CRUD
        Statement statement = connection.createStatement();


        //4 编写sql
        String sql = "select *  from smbms_address";

        //5 执行查询sql, 查询通过executeQuery()方法得到结果集ResultSet
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("id:" + resultSet.getInt("id"));
            System.out.println("contact:" + resultSet.getString("contact"));
            System.out.println("address:" + resultSet.getString("addressDesc"));
            System.out.println("postCode:" + resultSet.getInt("postCode"));
            System.out.println("postCode:" + resultSet.getLong("tel"));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

        //6 关闭链接释放资源(一定要做，先开的最后关)
        resultSet.close();
        statement.close();
        connection.close();

    }
}
