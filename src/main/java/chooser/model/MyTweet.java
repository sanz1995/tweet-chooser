package chooser.model;


public class MyTweet {

    private String id;

    private String text;

    private String user;

    private String lang;


    public MyTweet() {
    }

    public MyTweet(String id, String text, String user, String lang) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.lang=lang;
    }


    public String getId() {
        return id;
    }


    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }


    public String getLang() {
        return lang;
    }
}
