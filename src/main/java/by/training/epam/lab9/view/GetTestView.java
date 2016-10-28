package by.training.epam.lab9.view;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.dto.AnswerDTO;
import by.training.epam.lab9.bean.dto.QuestionDTO;
import by.training.epam.lab9.bean.dto.TestDTO;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.request.RequestWithID;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.bean.response.ResponseWithTestDTO;
import by.training.epam.lab9.view.exception.ViewException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexh on 26.10.2016.
 */
public class GetTestView extends View {

    @Override
    public Request getRequest() throws ViewException {
        Long testID;
        try {
            System.out.print("Enter the test id: ");
            testID = Long.parseLong(console.nextLine());
        }
        catch (NumberFormatException ex){
            throw new ViewException("Incorrect value");

        }
        return new RequestWithID(CommandEnum.SHOW_TEST, testID);
    }

    @Override
    public void showResponse(Response response) throws ViewException {
        if (response == null || response.getClass() != ResponseWithTestDTO.class){
            super.showResponse(response);
            return;
        }

        List<Boolean> testResults = new ArrayList<>();
        TestDTO test = ((ResponseWithTestDTO) response).getTestDTO();

        System.out.printf("%s [subject: %d.%s; error threshold: %d]\n", test.getName(),
                test.getSubject().getId(), test.getSubject().getName(), test.getErrorThreshold());

        for (QuestionDTO question : test.getQuestionSet()){
            System.out.printf("\t%s\n", question.getText());

            AnswerDTO[] answerDTOArray = question.getAnswerList()
                    .toArray(new AnswerDTO[question.getAnswerList().size()]);
            for (int i = 0; i < answerDTOArray.length; i++){
                System.out.printf("\t\t%d. %s\n", i + 1, answerDTOArray[i].getText());
            }

            boolean isCorrectAnswer = false;
            while (!isCorrectAnswer){
                try {
                    System.out.print("\tSelect number of answer: ");
                    byte answerCount = Byte.parseByte(console.nextLine());
                    testResults.add(answerDTOArray[answerCount - 1].getCorrect());
                    isCorrectAnswer = true;
                }
                catch (ArrayIndexOutOfBoundsException | NumberFormatException ex){
                    System.out.println("\t\tIncorrect value");
                }
            }
        }

        int mistakeNumber = test.getQuestionSet().size() - (int) testResults.stream().filter(s -> s).count();
        System.out.printf("\nResult: %s, ", mistakeNumber <= test.getErrorThreshold() ? "passed" : "not passed");
        System.out.printf("number of mistakes: %d.\n", mistakeNumber);
    }
}
