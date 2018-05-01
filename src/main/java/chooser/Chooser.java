package chooser;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Chooser implements CommandLineRunner {

    @Value("${twitter.consumerKey}")
    private String consumerKey;

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Autowired
    private KeywordRepository kr;

    private static RabbitTemplate rabbitTemplate;

    private Twitter twitter;

    private List<StreamListener> list;

    private Stream s;

    private String lastKeywords = "";

    public Chooser(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }



    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {

        String keywords = "";
        for(Keyword key : kr.findAll()){
            keywords+=key.getWord()+",";
        }


        keywords = keywords.substring(0,keywords.length()-1);


        if(!keywords.equals(lastKeywords)){

            if(s == null){
                twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);

                list = new ArrayList<StreamListener>();

                list.add(new TweetListener(rabbitTemplate));

            }else{

                s.close();
            }


            s = twitter.streamingOperations().filter(keywords,list);

            lastKeywords = keywords;
        }


    }

    @Override
    public void run(String... args){
    }

}
