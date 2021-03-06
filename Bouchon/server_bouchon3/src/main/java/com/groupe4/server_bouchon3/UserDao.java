package com.groupe4.server_bouchon3;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    //retrieval of the list of all existing user
    default List<User> getUsers (){
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            users.add(new User());
        }
        return users;
    }
    //retrieval of a user's id from his username.  We assume that a username is uniquejust like an id
    default int getUserId (String username){
        int id = 0;
        for(int i = 0; i < username.length(); i++){
            id += username.charAt(i);
        }
        return id;
    }
    //retrieval of a username
    default String getUsername (int user_id){
        return "random username";
    }
    //retrieval of a user's password 
    default String getPassword (int user_id){
        return "random password";
    }
    //retrieval of user's list of maps that he created
    default List<Maps> getPersonnelMaps (int user_id){
        List<Maps> personnelMaps = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            personnelMaps.add(new Maps());
        }
        return personnelMaps;
    }
    //retrieval of a user's list of map that other users shared with him
    default List<Maps> getMapsSharedToHim (int user_id){
        List<Maps> sharedToHim = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            sharedToHim.add(new Maps());
        }
        return sharedToHim;
    }
}
