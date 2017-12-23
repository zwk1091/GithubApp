package com.example.wenkun.githubapp;

import java.util.Observable;

/**
 * Created by Wenkun on 2017/12/21.
 */

public class Github {
    private String login;
    private String blog;
    private int id;
    public String getLogin() {return login;}
    public String getBlog() {return blog;}
    public int getId() {return id;}
    Github(String l,String b,int i) {
        login=l;
        blog=b;
        id=i;
    }
}
