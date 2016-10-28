package by.training.epam.lab9.bean.response;

import by.training.epam.lab9.bean.dto.TestDTO;

/**
 * Created by alexh on 26.10.2016.
 */
public class ResponseWithTestDTO extends Response {

    private TestDTO testDTO;


    public ResponseWithTestDTO(TestDTO testDTO){
        super();
        setTestDTO(testDTO);
    }


    public TestDTO getTestDTO() {
        return testDTO;
    }

    public void setTestDTO(TestDTO testDTO) {
        this.testDTO = testDTO;
    }
}
