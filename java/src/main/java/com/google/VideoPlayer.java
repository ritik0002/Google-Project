package com.google;

import java.util.List;
import java.util.Random;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    List<Video> list=videoLibrary.getVideos();
    bubblesort(list);

      for(int i=0;i<list.size();i++){
        String tags=list.get(i).getTags().toString();
        tags=tags.replaceAll(",","");
        System.out.println(list.get(i).getTitle()+" ("+list.get(i).getVideoId()+") "+tags);
    }
  }

  public static void bubblesort(List<Video>list ){
    for(int i=0;i<list.size()-1;i++){
      for(int j=0;j<list.size()-i-1;j++){
        if ((list.get(j).getTitle()).compareToIgnoreCase(list.get(j+1).getTitle()) > 0) {
          Video temp = list.get(j+1);
          list.set(j+1,list.get(j));
          list.set(j,temp);
        }
      }

    }
  }



  public void playVideo(String videoId) {
    boolean check = false;
    //sets playing state to true

    for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
      if (videoId.equals(videoLibrary.getVideos().get(i).getVideoId())) {
        check = true;

        //checks if any videos are currently playing
        for (int j = 0; j < videoLibrary.getVideos().size(); j++) {
          if (videoLibrary.getVideos().get(j).getPlaying() == true) {
            System.out.println("Stopping video: " + videoLibrary.getVideos().get(j).getTitle());
            videoLibrary.getVideos().get(j).setPlaying(false);
          }
        }
        videoLibrary.getVideos().get(i).setPlaying(true);
        System.out.println("Playing video: " + videoLibrary.getVideos().get(i).getTitle());
      }
    }
    if(check==false){
      System.out.println("Cannot play video: Video does not exist");
    }
  }



  public void stopVideo() {
    boolean check=false;
    for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
      if(videoLibrary.getVideos().get(i).getPlaying()==true){
        System.out.println("Stopping video: "+videoLibrary.getVideos().get(i).getTitle());
        videoLibrary.getVideos().get(i).setPlaying(false);
        check=true;
      }
    }
    if(check==false){
      System.out.println("Cannot stop video: No video is currently playing");
    }
  }

  public void playRandomVideo() {
    stopVideo();
    Random random=new Random();
    int num=random.nextInt(videoLibrary.getVideos().size());
    System.out.println("Playing video: "+videoLibrary.getVideos().get(num).getTitle());
  }

  public void pauseVideo() {
    boolean check=false;
    for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
      if(videoLibrary.getVideos().get(i).getPause()==true) {
        System.out.println("Video already paused: "+videoLibrary.getVideos().get(i).getTitle());
        check=true;
      }else if(videoLibrary.getVideos().get(i).getPlaying()==true){
        videoLibrary.getVideos().get(i).setPause(true);
        System.out.println("Pausing video: "+videoLibrary.getVideos().get(i).getTitle());
        check=true;
      }
      }
    if(check==false){
      System.out.println("Cannot pause video: No video is currently playing");
    }
  }

  public void continueVideo() {
    for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
      if (videoLibrary.getVideos().get(i).getPause() == true) {

      }
    }
  }

  public void showPlaying() {
    System.out.println("showPlaying needs implementation");
  }

  public void createPlaylist(String playlistName) {
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}