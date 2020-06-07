package com.mygdx.game;

public class User {

    public String name;
    public String login;
    public String password;
    public long highScore;
    public int money;
    public int magnetPurchased;
    public int wingsPurchased;
    public int rocketPurchased;
    public int doubleBonusPurchased;
    public boolean newHighScoreBool =false;

    public User(){}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        name = "admin";
        highScore = 0;
        money = 300;
        magnetPurchased = 10;
        wingsPurchased = 10;
        rocketPurchased = 10;
        doubleBonusPurchased = 10;
    }

    //getters
    public String getName() { return name; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public int getMoney() { return money; }
    public long getHighScore() { return highScore; }
    public int getMagnetPurchased() { return magnetPurchased; }
    public int getWingsPurchased() { return wingsPurchased; }
    public int getRocketPurchased() { return rocketPurchased; }
    public int getDoubleBonusPurchased() { return doubleBonusPurchased; }

    //setters
    public void setName(String name) { this.name = name; }
    public void setLogin(String login) { this.login = login; }
    public void setPassword(String password) { this.password = password; }
    public void setMoney(int money) { this.money = money; }
    public void setHighScore(long highScore) { this.highScore = highScore; }
    public void setMagnetPurchased(int magnetPurchased) { this.magnetPurchased = magnetPurchased; }
    public void setWingsPurchased(int wingsPurchased) { this.wingsPurchased = wingsPurchased; }
    public void setRocketPurchased(int rocketPurchased) { this.rocketPurchased = rocketPurchased; }
    public void setDoubleBonusPurchased(int doubleBonusPurchased) { this.doubleBonusPurchased = doubleBonusPurchased; }

    public void addMoney(int amount){ this.money += amount; }
    public void minusMoney(int amount){ this.money -= amount; }
    public void updateHighScore(long newScore){
        if(newScore>this.highScore) {
            this.highScore = newScore;
            newHighScoreBool = true;
        }
    }
    public void purchaseMagnet(){ this.magnetPurchased += 1;}
    public void purchaseWings(){ this.wingsPurchased += 1;}
    public void purchaseRocket(){ this.rocketPurchased += 1;}
    public void purchaseDoubleBonus(){ this.doubleBonusPurchased += 1;}
}
