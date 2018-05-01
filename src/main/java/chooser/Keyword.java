package chooser;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Keyword {

    @Id
    private String word;


    public Keyword() {
    }

    public Keyword(String word) {
        this.word = word;
    }


    public String getWord() {
        return word;
    }


}
