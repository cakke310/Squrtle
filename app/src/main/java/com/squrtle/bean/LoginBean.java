package com.squrtle.bean;

/**
 * Created by c_xuwei-010 on 2017/4/24.
 */
public class LoginBean extends BaseEntity {
    private String token;

    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
