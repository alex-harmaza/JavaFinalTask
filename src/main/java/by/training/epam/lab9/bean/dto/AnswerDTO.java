package by.training.epam.lab9.bean.dto;

/**
 * Created by alexh on 26.10.2016.
 */
public class AnswerDTO {

    private String text;
    private Boolean isCorrect;


    public AnswerDTO(){}

    public AnswerDTO(String text, Boolean isCorrect){
        setText(text);
        setCorrect(isCorrect);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerDTO answerDTO = (AnswerDTO) o;

        if (text != null ? !text.equals(answerDTO.text) : answerDTO.text != null) return false;
        return isCorrect != null ? isCorrect.equals(answerDTO.isCorrect) : answerDTO.isCorrect == null;

    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (isCorrect != null ? isCorrect.hashCode() : 0);
        return result;
    }
}
