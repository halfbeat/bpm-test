package es.jcyl.gss.redmine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
            .setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));

    public static void main(String[] args) throws Exception {

        Options options = new Options();

        options.addOption("h", "help", false, "Información de uso");
        options.addOption("u", "url", true, "URL del servidor redmine (o");
        options.addOption("a", "api-key", true, "Api key del servidor");
        options.addOption("f", "file", true, "nombre del fichero YAML con los datos de la carga");
        BasicParser parser = new BasicParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("help")) {
                printHelp(options);
                return;
            }
            boolean error = false;
            if (!cmd.hasOption("url")) {
                System.out.println("Debe indicar la opción url para indicar la URL del servicio REST de redmine");
                error = true;
            }
            if (!cmd.hasOption("file")) {
                System.out.println("Debe indicar el nombre del fichero yaml con la información de la carga");
                error = true;
            }
            if (!cmd.hasOption("api-key")) {
                System.out.println("Debe indicar la apli-key para la comunicación con el servidor redmine");
                error = true;
            }

            if (error) {
                printHelp(options);
                return;
            }
            String file = cmd.getOptionValue("file");
            String url = cmd.getOptionValue("url");
            String apiKey = cmd.getOptionValue("api-key");

            logger.debug("Parámetros de la ejecución:");
            logger.debug("file = {}\nurl = {}\napi-key = {}", (Object[]) new String[]{file, url, apiKey});

            RedmineManager gestorRedmine = RedmineManagerFactory.createWithApiKey(url, apiKey);

            try (FileInputStream is = new FileInputStream(file)) {
                logger.info("Leyendo fichero YAML con la configuración ...");
                InformacionRellenoRedmine informacion = mapper.readValue(is, InformacionRellenoRedmine.class);
                logger.info("Fichero leido con éxito");
                logger.debug("Información laida: {}", informacion);
                RellenadorRedmine rellenadorRedmine = new RellenadorRedmine(gestorRedmine, informacion);
                rellenadorRedmine.rellenar();
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            printHelp(options);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            logger.debug(e.getMessage(), e);
        }
    }

    private static void printHelp(Options options) {
        new HelpFormatter().printHelp(Main.class.getCanonicalName(), options);
    }
}
