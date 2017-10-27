package org.demo.dao;

import org.springframework.stereotype.Repository;

/**
 * @auther HuJiaqi
 * @createTime 2017年10月26日
 * @discription
 */
@Repository
public class UserDao {

    /*
     * 一共有3个人
     * id level
     * 1  boss
     * 2  manager
     * 3  emp
     */

    public String getLevelByUserId(String userId) {
        switch (userId) {
            case "1":
                return "boss";
            case "2":
                return "manager";
            case "3":
                return "emp";
            default:
                return null;
        }
    }

}
