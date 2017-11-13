package bootstrap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class LifeCycle {
    @PostConstruct
    void init() {
        System.err.println("I'm alive!");

    }
}
