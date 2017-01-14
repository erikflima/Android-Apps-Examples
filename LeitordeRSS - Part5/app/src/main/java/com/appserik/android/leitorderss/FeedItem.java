package com.appserik.android.leitorderss;


public class FeedItem {

    //Campos para armazenar o conteudo dentro das tags do XML
    String  title;
    String  link;
    String  description;
    String  pubDate;
    String  thumbnailUrl;




    //**********Getters and Setters***********//



    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }


//--------




    public String getThumbnailUrl() {
        return thumbnailUrl;
    }


    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    //--------




    public String getPubDate() {
        return pubDate;
    }


    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    //--------


    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }


    //--------


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    //--------
}//class
