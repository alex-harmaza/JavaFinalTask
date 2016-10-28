package by.training.epam.lab9.utility;

import by.training.epam.lab9.bean.entity.*;
import by.training.epam.lab9.entity.*;

/**
 * Created by alexh on 28.10.2016.
 */
public class XmlEntityConverter {

    public static User toUser(XmlUser xmlUser){
        return new User(xmlUser.getLogin(), xmlUser.getPassword(), xmlUser.getRole());
    }


    public static Answer toAnswer(XmlAnswer xmlAnswer){
        Answer answer = new Answer();
        answer.setCorrect(xmlAnswer.isCorrect());
        answer.setText(xmlAnswer.getText());
        answer.setQuestionID(xmlAnswer.getQuestionID());
        return answer;
    }


    public static Question toQuestion(XmlQuestion xmlQuestion){
        Question question = new Question();
        question.setId(xmlQuestion.getId());
        question.setTestID(xmlQuestion.getId());
        question.setText(xmlQuestion.getText());
        return question;
    }


    public static Test toTest(XmlTest xmlTest){
        Test test = new Test();
        test.setId(xmlTest.getId());
        test.setName(xmlTest.getName());
        test.setErrorThreshold(xmlTest.getErrorThreshold());
        test.setSubject(new Subject(xmlTest.getId(), null));
        return test;
    }

    public static Subject toSubject(XmlSubject xmlSubject){
        return new Subject(xmlSubject.getId(), xmlSubject.getName());
    }

}
