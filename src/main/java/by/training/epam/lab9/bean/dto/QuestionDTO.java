package by.training.epam.lab9.bean.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexh on 26.10.2016.
 */
public class QuestionDTO {

    private String text;
    private Set<AnswerDTO> answerSet;


    public QuestionDTO(){
        answerSet = new HashSet<>();
    }

    public QuestionDTO(String text){
        this();
        setText(text);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<AnswerDTO> getAnswerList() {
        return answerSet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionDTO that = (QuestionDTO) o;

        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return answerSet != null ? answerSet.equals(that.answerSet) : that.answerSet == null;

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (answerSet != null ? answerSet.hashCode() : 0);
        return result;
    }
}
