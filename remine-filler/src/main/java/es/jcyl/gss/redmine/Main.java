package es.jcyl.gss.redmine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {
    public static void main(String[] args) throws Exception {
        Options options = new Options();

        options.addOption("u", "url", true, "URL del servidor redmine");
        options.addOption("a", "api-key", true, "Apli key del servidor");


    }
}
