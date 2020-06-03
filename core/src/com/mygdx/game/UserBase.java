package com.mygdx.game;

import com.badlogic.gdx.Screen;

import java.util.ArrayList;

public class UserBase {


    public ArrayList<User> users;


    UserBase() {
        users = new ArrayList<>();
        users.add(new User("login","password"));
        users.add(new User("user1","pass"));
    }




    public boolean checkUser(User u) {
        boolean res = false;
        for (User k:users) {
            if ((k.login.equals(u.login)) && (k.password.equals(u.password))) {
                res=true;
                break;
            }
        }
        return res;
    }




}
