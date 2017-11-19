package bootstrap;

import org.wso2.msf4j.HttpStreamer;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Path("/greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Greeting greeting(@QueryParam("name") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }


    @POST
    @Path("/{fileName}")
    public void postFile(@Context HttpStreamer httpStreamer,
                         @PathParam("fileName") String fileName) throws IOException {
        httpStreamer.callback(new HttpStreamHandlerImpl(fileName));
    }}