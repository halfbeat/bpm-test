package es.jcyl.gss.redmine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.taskadapter.redmineapi.RedmineManager;

import java.io.InputStream;

public class Filler {
    private final RedmineManager redmineManager;
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public Filler(RedmineManager redmineManager) {
        this.redmineManager = redmineManager;
    }

    public void fill(InputStream fillDataYaml) {

    }


}
