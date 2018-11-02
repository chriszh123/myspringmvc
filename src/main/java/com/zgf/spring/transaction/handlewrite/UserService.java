/**
 * Copyright (C) 2006-2017 Wisedu All rights reserved
 * Author：zhangguifeng
 * Date：2018/9/28
 * Description: UserService
 */
package com.zgf.spring.transaction.handlewrite;

import javax.sql.DataSource;

/**
 * UserService
 *
 * @author zhangguifeng
 * @create 2018-09-28 15:19
 **/
public class UserService {

    private UserAccountDao userAccountDao;
    private UserOrderDao userOrderDao;
    private TransactionManager transactionManager;

    public UserService(DataSource dataSource) {
        this.userAccountDao = new UserAccountDao(dataSource);
        this.userOrderDao = new UserOrderDao(dataSource);
        this.transactionManager = new TransactionManager(dataSource);
    }

    public void action() {
        try {
            this.transactionManager.start();
            this.userAccountDao.buy();
            this.userOrderDao.order();
            this.transactionManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
