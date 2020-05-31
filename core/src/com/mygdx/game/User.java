package com.mygdx.game;

import com.badlogic.gdx.Screen;

public class User {
    public String login;
    public String password;
    public long score;
    public int money;


    User (String login, String password) {
        this.login = login;
        this.password = password;
    }
}
