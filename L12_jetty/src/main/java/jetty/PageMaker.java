package jetty;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by carapooh on 15.10.2017.
 */
public class PageMaker {

    private static final String TEMPLATE_DIR = "template";
    private static PageMaker ourInstance = new PageMaker();
    private final Configuration configuration;

    public static PageMaker getInstance() {
        return ourInstance;
    }

    private PageMaker() {
        configuration = new Configuration();
    }

    public String getPage(String templ, Map<String, Object> vars) throws IOException {
        try (Writer writer = new StringWriter()){
            Template template = configuration.getTemplate(TEMPLATE_DIR + File.separator + templ);
            template.process(vars, writer);
            return writer.toString();
        }
        catch (TemplateException te){
            te.printStackTrace();
            throw new IOException();
        }
    }
}
