package by.training.epam.lab9.bean.response;

import by.training.epam.lab9.bean.entity.Test;

import java.util.List;

/**
 * Created by alexh on 26.10.2016.
 */
public class ResponseWithTestList extends Response {

    private List<Test> testList;


    public ResponseWithTestList(List<Test> testList){
        setTestList(testList);
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }
}
