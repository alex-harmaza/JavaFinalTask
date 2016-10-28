package by.training.epam.lab9.store;

import by.training.epam.lab9.entity.XmlData;
import by.training.epam.lab9.parser.DomParser;

import java.io.IOException;

/**
 * Created by alexh on 27.10.2016.
 */
public class EntityStore {

    private final static EntityStore instance = new EntityStore();
    private final static String XML_RESOURCE_FILE_PATH = "test-data.xml";
    private XmlData xmlData;


    public static EntityStore getInstance() {
        return instance;
    }


    private EntityStore() {
        try {
            xmlData = new DomParser()
                    .parse(this.getClass().getClassLoader()
                            .getResource(XML_RESOURCE_FILE_PATH).getPath());
        }
        catch (IOException ex){
            throw new RuntimeException(ex);
        }
    }


    public XmlData getXmlData(){
        return xmlData;
    }

}
