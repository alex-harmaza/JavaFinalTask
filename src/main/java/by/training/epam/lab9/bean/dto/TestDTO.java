package by.training.epam.lab9.bean.dto;

import by.training.epam.lab9.bean.entity.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexh on 26.10.2016.
 */
public class TestDTO {

    private String name;
    private Subject subject;
    private Byte errorThreshold;
    private Set<QuestionDTO> questionSet;


    public TestDTO(){
        questionSet = new HashSet<>();
    }

    public TestDTO(String name, Subject subject, Byte errorThreshold){
        this();
        setName(name);
        setSubject(subject);
        setErrorThreshold(errorThreshold);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Byte getErrorThreshold() {
        return errorThreshold;
    }

    public void setErrorThreshold(Byte errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    public Set<QuestionDTO> getQuestionSet() {
        return questionSet;
    }

}
