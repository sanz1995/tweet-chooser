package chooser;


public class MyTweet {

    private String id;

    private String text;

    private String user;


    public MyTweet() {
    }

    public MyTweet(String id, String text, String user) {
        this.id = id;
        this.text = text;
        this.user = user;
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



}
