package com.modavagostar.order;

/**
 * Created by Xaniar on 4/16/2018.
 */

import java.util.ArrayList;

public class GroupItemsInfo {

    private String listName;
    private ArrayList<ChildItemsInfo> list = new ArrayList<ChildItemsInfo>();

    public String getName() {
        return listName;
    }

    public void setName(String songListName) {
        this.listName = songListName;
    }

    public ArrayList<ChildItemsInfo> getSongName() {
        return list;
    }

    public void setPlayerName(ArrayList<ChildItemsInfo> songName) {
        this.list = songName;
    }

}