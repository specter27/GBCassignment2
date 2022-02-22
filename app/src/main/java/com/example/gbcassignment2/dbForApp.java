package com.example.gbcassignment2;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class dbForApp {
    /*
     This is a class that act as a Database of the validCredentials for making a login in the app
     prevent us from  passing data from one activity to another
  */
    private static dbForApp dbforapp;
    private static String appTitle = "Learning HTML and CSS App";

    // This array list will provide all the list of valid credentials that are valid
    private ArrayList<User> validUserCredentials =
            new ArrayList<>(Arrays.asList(new User("abcd","1234")));

    private dbForApp(){
    }

    public static dbForApp getDbForAppSingleton(){
        if(dbforapp == null)
            dbforapp = new dbForApp();
        return dbforapp;
    }

    public static String getAppTitle() {
        return appTitle;
    }

    public ArrayList<User> getValidUserCredentialsList(){
        return validUserCredentials;
    }

    public String checkCredentialValidity(User currUser){
        String status=null;
        for(User userCredentials: validUserCredentials){
            //checking weather the userName exist or not
            if(currUser.getUserName().equals(userCredentials.getUserName())){
                if(currUser.getPassword().equals(userCredentials.getPassword())){
                    //status for the valid credentials
                    status = "successfulLogin";
                }else{
                    status = "Username/Password combination incorrect";
                }
            }else{
                status = "Username does not exist";
            }
        }
        return status;
    }
}
