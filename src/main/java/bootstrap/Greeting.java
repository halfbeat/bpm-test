package bootstrap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Greeting {
    private final long id;
    private final String content;

    Greeting() {
        this(0,null);
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @XmlElement
    public long getId() {
        return id;
    }
    @XmlElement
    public String getContent() {
        return content;
    }
}