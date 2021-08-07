package com.google;

import java.util.ArrayList;
import java.util.List;

/** A class used to represent a Playlist */
class VideoPlaylist {
    private  String name;
    private List<Video>  list;

    VideoPlaylist(String n){
        name=n;
        list=new ArrayList<>();
    }

    /** Returns the title of the video. */
    String getName() {
        return name;
    }

    List<Video> getVideo(){ return list;};
}
