package com.postat;

import java.util.ArrayList;

public class Posts {
    public static ArrayList<Post> postsList;
    private static int post_id = 0;

    public static void initPostsList(){
        DatabaseWrapper databaseWrapper = new DatabaseWrapper();
        postsList = databaseWrapper.getPosts();
    }

    public static Post getCurrentPost(){
        initPostsList();
        if(postsList.isEmpty()){
            Post temp = new Post("No","Posts","Please add posts in the database");
            return temp;
        }
        return postsList.get(post_id);
    }
    public static Post getNextPost(){
        initPostsList();
        if(postsList.size()-1>post_id){
            post_id++;
            return postsList.get(post_id);
        }else{
            return null;
        }
    }
    public static Post getPreviousPost() {
        initPostsList();
        if(post_id>0){
            post_id--;
            return postsList.get(post_id);
        }else
            return null;
    }
}
