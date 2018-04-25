package com.example.mike.mikeleonad340;

import java.util.ArrayList;

public class createList {
    public static ArrayList<String> list(String[][] movies , int grab){
        ArrayList<String> zList = new ArrayList<>();
        for(int i = 0; i <= movies.length - 1; i++){
            for (int j = 0; j <= movies[i].length - 1; j++){
                if(j == grab) {
                    zList.add(movies[i][j]);
                }
            }
            System.out.println(zList.get(i));
        }

        return zList;
    }
}
