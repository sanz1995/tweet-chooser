package chooser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;

/**
 * Created by jorge on 29/03/18.
 */
public class TweetListener implements StreamListener {

    private RabbitTemplate r;

    public TweetListener(RabbitTemplate rabbit){
        r = rabbit;

        r.convertAndSend("tweet","hola");
    }


    @Override
    public void onTweet(Tweet tweet) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


        try {
            
            String json = ow.writeValueAsString((Object)tweet);
            r.convertAndSend("tweet",json);

        } catch (JsonProcessingException e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {

    }

    @Override
    public void onLimit(int numberOfLimitedTweets) {

    }

    @Override
    public void onWarning(StreamWarningEvent warningEvent) {

    }
}
