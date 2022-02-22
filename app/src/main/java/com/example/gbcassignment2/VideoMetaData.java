package com.example.gbcassignment2;

public class VideoMetaData {

    private String videoTitle;
    private String videoDescription;
    private String videoURL;
    private String videoDuration;
    //This string will hold the notes added for a particular video lecture by the user
    private String videoLectureNotes;
    //To maintain the record weather the video is completed or not
    private boolean isCompleted;

    public VideoMetaData(String videoTitle, String videoDescription, String videoURL, String videoDuration) {
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.videoURL = videoURL;
        this.videoDuration = videoDuration;
    }


    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public String getVideoLectureNotes() {
        return videoLectureNotes;
    }

    public void setVideoLectureNotes(String videoLectureNotes) {
        this.videoLectureNotes = videoLectureNotes;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }


    @Override
    public String toString() {
        return "VideoMetaData{" +
                "videoTitle='" + videoTitle + '\'' +
                ", videoDescription='" + videoDescription + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", videoDuration='" + videoDuration + '\'' +
                ", videoLectureNotes='" + videoLectureNotes + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
