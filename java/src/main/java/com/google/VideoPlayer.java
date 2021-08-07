package com.google;

import java.util.*;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private final  List<VideoPlaylist> playlist=new ArrayList<>();

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
              videoLibrary.getVideos().get(j).setPause(false);

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
        videoLibrary.getVideos().get(i).setPause(false);
        check=true;
      }
    }
    if(check==false){
      System.out.println("Cannot stop video: No video is currently playing");
    }
  }

  public void playRandomVideo() {
    for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
      if (videoLibrary.getVideos().get(i).getPlaying() == true) {
        stopVideo();
      }
    }
      Random random = new Random();
      int num = random.nextInt(videoLibrary.getVideos().size());
      System.out.println("Playing video: " + videoLibrary.getVideos().get(num).getTitle());
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
    boolean check=false;
    for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
      if (videoLibrary.getVideos().get(i).getPause() == true) {
        videoLibrary.getVideos().get(i).setPause(false);
        System.out.println("Continuing video: " + videoLibrary.getVideos().get(i).getTitle());
        return;
      } else if (videoLibrary.getVideos().get(i).getPlaying() == true) {
        check = true;
      }
    }
      if(check==true){
        System.out.println("Cannot continue video: Video is not paused");
        return;
      }
    System.out.println("Cannot continue video: No video is currently playing");

  }

  public void showPlaying() {
    for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
      if (videoLibrary.getVideos().get(i).getPause() == true) {
        String tags = videoLibrary.getVideos().get(i).getTags().toString();
        tags = tags.replaceAll(",", "");
        tags += " - PAUSED";
        System.out.println("Currently playing: " + videoLibrary.getVideos().get(i).getTitle() + " (" + videoLibrary.getVideos().get(i).getVideoId() + ") " + tags);
        return;
      } else if (videoLibrary.getVideos().get(i).getPlaying() == true) {
        String tags = videoLibrary.getVideos().get(i).getTags().toString();
        tags = tags.replaceAll(",", "");
        System.out.println("Currently playing: " + videoLibrary.getVideos().get(i).getTitle() + " (" + videoLibrary.getVideos().get(i).getVideoId() + ") " + tags);
        return;

      }
    }
      System.out.println("No video is currently playing");
    }


  public void createPlaylist(String playlistName) {
    playlistName=playlistName.replaceAll("\\s","");
    for(int i=0;i<playlist.size();i++){
      if(playlistName.equalsIgnoreCase(playlist.get(i).getName())){
        System.out.println("Cannot create playlist: A playlist with the same name already exists");
        return;
      }
    }
      VideoPlaylist  x= new VideoPlaylist(playlistName);
      playlist.add(x);
    System.out.println("Successfully created new playlist: "+playlistName);
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    for(int i=0;i<playlist.size();i++) {
      if(playlist.get(i).getName().equalsIgnoreCase(playlistName)){
        for(int j=0;j<videoLibrary.getVideos().size();j++){  //goes through video library check if it exists
          if (videoId.equals(videoLibrary.getVideos().get(j).getVideoId())) {

            //checks if video exists in the playlist
            for(int k=0;k<playlist.get(i).getVideo().size();k++) {
                if(playlist.get(i).getVideo().get(k).getVideoId().equals(videoId)){
                  System.out.println("Cannot add video to "+playlistName+": Video already added");
                  return;
                }
            }
            playlist.get(i).getVideo().add(videoLibrary.getVideo(videoId));
            System.out.println("Added video to "+playlistName+": "+videoLibrary.getVideos().get(j).getTitle());
            return;
          }
          }
        System.out.println("Cannot add video to "+playlistName+": Video does not exist");
        return;
      }
    }
      System.out.println("Cannot add video to "+playlistName+": Playlist does not exist");
  }

  public void showAllPlaylists() {
    int length=playlist.size();

    if(length==0){
      System.out.println("No playlists exist yet");
    }else{
      System.out.println("Showing all playlists:");
      for(int i=length-1;i>=0;i--){
        System.out.println(" "+playlist.get(i).getName());
      }
    }
  }

  public void showPlaylist(String playlistName) {
    for(int i=0;i<playlist.size();i++) {
      if (playlist.get(i).getName().equalsIgnoreCase(playlistName)) {
        System.out.println("Showing playlist: " + playlistName);
        if (playlist.get(i).getVideo().size() == 0) {
          System.out.println("No videos here yet");
          return;
        } else {
          for (int j = 0; j < playlist.get(i).getVideo().size(); j++) {
            String tags=playlist.get(i).getVideo().get(j).getTags().toString();
            tags=tags.replaceAll(",","");
            System.out.println(playlist.get(i).getVideo().get(j).getTitle()+" ("+playlist.get(i).getVideo().get(j).getVideoId()+") "+tags);
          }
          return;
        }
      }
    }
    System.out.println("Cannot show playlist "+playlistName+": Playlist does not exist");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    //checks through whole playlist
    for(int i=0;i<playlist.size();i++) {
      if (playlist.get(i).getName().equalsIgnoreCase(playlistName)) {

      //iterates through videos in playlist
        for (int j = 0; j < playlist.get(i).getVideo().size(); j++) {
          if(playlist.get(i).getVideo().get(j).getVideoId().equals(videoId)){
            System.out.println("Removed video from "+playlistName+": "+playlist.get(i).getVideo().get(j).getTitle());
            playlist.get(i).getVideo().remove(j);
            return;
          }
        }
        for(int k=0;k<videoLibrary.getVideos().size();k++){
          if(videoLibrary.getVideos().get(k).getVideoId().equals(videoId)){
            System.out.println("Cannot remove video from "+playlistName+": Video is not in playlist");
            return;
          }
        }
        System.out.println("Cannot remove video from "+playlistName+": Video does not exist");
        return;
        }

      }
    System.out.println("Cannot remove video from "+playlistName+": Playlist does not exist");
  }

  public void clearPlaylist(String playlistName) {
    for (int i = 0; i < playlist.size(); i++) {
      if (playlist.get(i).getName().equalsIgnoreCase(playlistName)) {
        System.out.println("Successfully removed all videos from "+playlistName);
        for (int j = 0; j < playlist.get(i).getVideo().size(); j++) {
          playlist.get(i).getVideo().remove(j);
        }
      return;
      }
    }
    System.out.println("Cannot clear playlist "+playlistName+": Playlist does not exist");

  }

  public void deletePlaylist(String playlistName) {
    for (int i = 0; i < playlist.size(); i++) {
      if (playlist.get(i).getName().equalsIgnoreCase(playlistName)) {
        System.out.println("Deleted playlist: "+playlistName);
        playlist.remove(i);
        return;
      }
    }
    System.out.println("Cannot delete playlist "+playlistName+": Playlist does not exist");
  }

  public String Input(String message){
    String input;
    Scanner scanner=new Scanner(System.in);
    System.out.println(message);
    input=scanner.nextLine();
    return input;
  }

  public void ListCheck(List<Video> list){
    int num;
    for(int j=0;j<list.size();j++){
      String tags=list.get(j).getTags().toString();
      tags=tags.replaceAll(",","");
      System.out.println((j+1)+") "+list.get(j).getTitle()+" ("+list.get(j).getVideoId()+") "+tags);
    }
    String input=Input("Would you like to play any of the above? If yes, specify the number of the video.\n" +
            "If your answer is not a valid number, we will assume it's a no.");
    try{
      num=Integer.parseInt(input);
    } catch(Exception e){
      return;
    }
    if(num>list.size() || num<=0){
      return;
    }else{
      String id=list.get(num-1).getVideoId();
      playVideo(id);
    }
  }
  public void searchVideos(String searchTerm) {
    List<Video> list = new ArrayList<>();
    for(int i=0;i<videoLibrary.getVideos().size();i++){
      if(videoLibrary.getVideos().get(i).getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
        list.add(videoLibrary.getVideos().get(i));
      }
    }
    if(list.size()==0){
      System.out.println("No search results for "+searchTerm);
      return;
    }
    bubblesort(list);
    System.out.println("Here are the results for "+searchTerm+":");
    ListCheck(list);

  }

  public void searchVideosWithTag(String videoTag) {
    List<Video> list = new ArrayList<>();
    for(int i=0;i<videoLibrary.getVideos().size();i++){
      if(videoLibrary.getVideos().get(i).getTags().contains(videoTag.toLowerCase())){
        list.add(videoLibrary.getVideos().get(i));
      }
    }
    if(list.size()==0){
      System.out.println("No search results for "+videoTag);
      return;
    }
    bubblesort(list);
    System.out.println("Here are the results for "+videoTag+":");
    ListCheck(list);
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