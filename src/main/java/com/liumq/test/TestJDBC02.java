package com.liumq.test;

import java.sql.*;

/**
 * 使用 PreparedStatement 预编译的sql
 * 防止sql注入 等 功能
 */
public class TestJDBC02 {
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

        //3 编写sql
        String sql = "insert into smbms_address(id,contact,addressDesc,postCode,tel) values (?,?,?,?,?)";

        //4 预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 8);//给第1个占位符的值赋值为1
        preparedStatement.setString(2, "柳梦琪");//给第2个占位符的值赋值为柳梦琪
        preparedStatement.setString(3, "浙江省杭州市余杭区仓溢东苑");//给第3个占位符的值赋值为1
        preparedStatement.setInt(4, 111101);//给第4个占位符的值赋值为1
        preparedStatement.setLong(5, 15527049978L);//给第5个占位符的值赋值为1

        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("插入成功");
        }
        preparedStatement.close();
        connection.close();


    }
}
