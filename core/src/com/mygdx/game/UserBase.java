package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserBase {
    public ArrayList<User> users;
    int userNumber;
    public String ss="";

    UserBase() {
        users = new ArrayList<User>();
        userNumber=0;
        users.add(new User("login","password"));
        users.add(new User("user1","pass"));
        users.add(new User("l", "p"));
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

    public User returnUser(User u) {
        User res = null;
        for (User k:users) {
            if ((k.login.equals(u.login)) && (k.password.equals(u.password))) {
                res=k;
                break;
            }
        }
        return res;
    }


    public void renew() throws IOException {
        users = new ArrayList<User>();
        FileReader fileReader = new FileReader("/users/lizaboiarchuk/desktop/users.txt");
        Scanner sc = new Scanner(fileReader);
        String s="";

        while (sc.hasNextLine()) {
            s=sc.nextLine();
            System.out.println(s);
            String[] all = s.split(" ");
            User u = new User();
            u.name = all[0];
            u.login = all[1];
            u.password = all[2];
            u.highScore = Integer.parseInt(all[3]);
            u.money = Integer.parseInt(all[4]);
            u.magnetPurchased = Integer.parseInt(all[5]);
            u.wingsPurchased = Integer.parseInt(all[6]);
            u.rocketPurchased = Integer.parseInt(all[7]);
            u.doubleBonusPurchased = Integer.parseInt(all[8]);
            users.add(u);
            System.out.println(u.login);
        }

        fileReader.close();

    }


    public void write() {
        try {
            ss="";
            FileWriter fileWriter  = new FileWriter("/users/lizaboiarchuk/desktop/users.txt");
            for (User newUser:users) {
                String k = newUser.name + " " + newUser.login + " " + newUser.password + " " + newUser.highScore + " " + newUser.money + " " + newUser.magnetPurchased+ " " + newUser.wingsPurchased+ " " + newUser.rocketPurchased +" " + newUser.doubleBonusPurchased + " k";
                fileWriter.append(k+ System. lineSeparator());
              //  fileWriter.write(k);
            }

            fileWriter.close();
       //     ss+=" k";
        //    System.out.println(ss+ " k" + "write");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
