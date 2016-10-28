package by.training.epam.lab9.bean.response;

import by.training.epam.lab9.bean.entity.Subject;

import java.util.List;

/**
 * Created by alexh on 27.10.2016.
 */
public class ResponseWithSubjectList extends Response {

    private List<Subject> subjectList;


    public ResponseWithSubjectList(List<Subject> subjectList){
        setSubjectList(subjectList);
    }


    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
}
