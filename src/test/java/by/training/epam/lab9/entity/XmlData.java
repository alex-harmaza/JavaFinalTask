package by.training.epam.lab9.entity;

/**
 * Created by alexh on 27.10.2016.
 */
public class XmlData {

    private XmlUser user;
    private XmlAnswer answer;
    private XmlQuestion question;
    private XmlSubject subject;
    private XmlTest test;


    public XmlUser getUser() {
        return user;
    }

    public void setUser(XmlUser user) {
        this.user = user;
    }

    public XmlAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(XmlAnswer answer) {
        this.answer = answer;
    }

    public XmlQuestion getQuestion() {
        return question;
    }

    public void setQuestion(XmlQuestion question) {
        this.question = question;
    }

    public XmlSubject getSubject() {
        return subject;
    }

    public void setSubject(XmlSubject subject) {
        this.subject = subject;
    }

    public XmlTest getTest() {
        return test;
    }

    public void setTest(XmlTest test) {
        this.test = test;
    }
}
