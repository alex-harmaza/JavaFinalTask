package by.training.epam.lab9.parser;

import by.training.epam.lab9.bean.entity.UserRole;
import by.training.epam.lab9.entity.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by alexh on 27.10.2016.
 */
public class DomParser {

    private DocumentBuilderFactory factory;


    public DomParser(){
        factory = DocumentBuilderFactory.newInstance();
    }


    public XmlData parse(String xmlFilePath) throws IOException {
        if (!isValidateXml(new File(xmlFilePath))){
            throw new IOException("Not valid xml file");
        }
        XmlData data;
        try (FileInputStream stream = new FileInputStream(xmlFilePath)) {
            Element document = factory.newDocumentBuilder().parse(stream).getDocumentElement();
            data = new XmlData();
            data.setAnswer(convertToAnswer((Element) document.getElementsByTagName("answer").item(0)));
            data.setUser(convertToUser((Element) document.getElementsByTagName("user").item(0)));
            data.setQuestion(convertToQuestion((Element) document.getElementsByTagName("question").item(0)));
            data.setSubject(convertToSubject((Element)document.getElementsByTagName("subject").item(0)));
            data.setTest(convertToTest((Element) document.getElementsByTagName("test").item(0)));
        } catch (SAXException | ParserConfigurationException e) {
            throw new IOException(e.getMessage(), e);
        }
        return data;
    }


    private XmlAnswer convertToAnswer(Element elem){
        NodeList fields = elem.getChildNodes();
        XmlAnswer answer = new XmlAnswer();
        for (int i = 0; i < fields.getLength(); i++){
            if (fields.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            Element element = (Element) fields.item(i);
            switch (element.getTagName().toUpperCase()){
                case "TEXT": answer.setText(element.getTextContent()); break;
                case "QUESTION-ID": answer.setQuestionID(Long.parseLong(element.getTextContent())); break;
                case "IS-CORRECT": answer.setCorrect(Boolean.parseBoolean(element.getTextContent())); break;
            }
        }
        return answer;
    }


    private XmlQuestion convertToQuestion(Element elem){
        NodeList fields = elem.getChildNodes();
        XmlQuestion question = new XmlQuestion();
        for (int i = 0; i < fields.getLength(); i++){
            if (fields.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            Element element = (Element) fields.item(i);
            switch (element.getTagName().toUpperCase()){
                case "ID": question.setId(Long.parseLong(element.getTextContent())); break;
                case "TEST-ID": question.setTestID(Long.parseLong(element.getTextContent())); break;
                case "TEXT": question.setText(element.getTextContent()); break;
            }
        }
        return question;
    }


    private XmlTest convertToTest(Element elem){
        NodeList fields = elem.getChildNodes();
        XmlTest test = new XmlTest();
        for (int i = 0; i < fields.getLength(); i++){
            if (fields.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            Element element = (Element) fields.item(i);
            switch (element.getTagName().toUpperCase()){
                case "ID": test.setId(Long.parseLong(element.getTextContent())); break;
                case "NAME": test.setName(element.getTextContent()); break;
                case "ERROR-THRESHOLD": test.setErrorThreshold(Byte.parseByte(element.getTextContent())); break;
                case "SUBJECT-ID": test.setSubjectID(Long.parseLong(element.getTextContent())); break;
            }
        }
        return test;
    }


    private XmlSubject convertToSubject(Element elem){
        NodeList fields = elem.getChildNodes();
        XmlSubject subject = new XmlSubject();
        for (int i = 0; i < fields.getLength(); i++){
            if (fields.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            Element element = (Element) fields.item(i);
            switch (element.getTagName().toUpperCase()){
                case "ID": subject.setId(Long.parseLong(element.getTextContent())); break;
                case "TEXT": subject.setName(element.getTextContent()); break;
            }
        }
        return subject;
    }


    private XmlUser convertToUser(Element elem){
        NodeList fields = elem.getChildNodes();
        XmlUser user = new XmlUser();
        for (int i = 0; i < fields.getLength(); i++){
            if (fields.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            Element element = (Element) fields.item(i);
            switch (element.getTagName().toUpperCase()){
                case "LOGIN": user.setLogin(element.getTextContent()); break;
                case "PASSWORD": user.setPassword(element.getTextContent()); break;
                case "ROLE": user.setRole(UserRole.valueOf(element.getTextContent())); break;
            }
        }
        return user;
    }


    private boolean isValidateXml(File file) throws IOException {
        boolean result = true;
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        try {
            Schema schema = factory.newSchema(this.getClass().getClassLoader().getResource("test-data-schema.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
        }
        catch (SAXException ex){
            result = false;
        }
        return result;
    }

}
