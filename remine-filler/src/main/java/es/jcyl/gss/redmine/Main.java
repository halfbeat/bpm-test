package es.jcyl.gss.redmine;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) throws Exception {
        Options options = new Options();

        options.addOption("u", "url", true, "URL del servidor redmine");
        options.addOption("a", "api-key", true, "Apli key del servidor");

        BasicParser parser = new BasicParser();
        try {
            parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage() );
            new HelpFormatter().printHelp(Main.class.getCanonicalName(), options );
        }
    }
}
