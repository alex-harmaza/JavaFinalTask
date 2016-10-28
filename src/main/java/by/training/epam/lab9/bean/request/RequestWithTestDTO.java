package by.training.epam.lab9.bean.request;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.dto.TestDTO;

/**
 * Created by alexh on 26.10.2016.
 */
public class RequestWithTestDTO extends Request {

    private TestDTO testDTO;


    public RequestWithTestDTO(CommandEnum command, TestDTO testDTO){
        super(command);
        setTestDTO(testDTO);
    }

    public TestDTO getTestDTO() {
        return testDTO;
    }

    public void setTestDTO(TestDTO testDTO) {
        this.testDTO = testDTO;
    }
}
