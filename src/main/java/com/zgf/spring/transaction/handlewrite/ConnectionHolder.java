/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: ConnectionHolder
 */
package com.zgf.spring.transaction.handlewrite;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * ConnectionHolder
 *
 * @author zhangguifeng
 * @create 2018-09-28 14:30
 **/
public class ConnectionHolder {
    private Map<DataSource, Connection> map = new HashMap<>(16);

    public Connection getConnectionByDateSource(DataSource dataSource) throws SQLException {
        Connection connection = map.get(dataSource);
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
            map.put(dataSource, connection);
        }

        return connection;
    }
}
