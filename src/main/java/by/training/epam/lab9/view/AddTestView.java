package by.training.epam.lab9.view;

import by.training.epam.lab9.CommandEnum;
import by.training.epam.lab9.bean.dto.AnswerDTO;
import by.training.epam.lab9.bean.dto.QuestionDTO;
import by.training.epam.lab9.bean.dto.TestDTO;
import by.training.epam.lab9.bean.entity.Subject;
import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.request.RequestWithTestDTO;
import by.training.epam.lab9.view.exception.ViewException;

/**
 * Created by alexh on 26.10.2016.
 */
public class AddTestView extends View {

    @Override
    public Request getRequest() throws ViewException {
        TestDTO testDTO = new TestDTO();
        try {
            System.out.print("Enter the name: ");
            testDTO.setName(console.nextLine().trim());

            System.out.print("Enter the error threshold: ");
            testDTO.setErrorThreshold(Byte.parseByte(console.nextLine()));

            System.out.print("Enter the subject: ");
            Subject subject = console.hasNextLong()
                    ? new Subject(Long.parseLong(console.nextLine()), null)
                    : new Subject(null, console.nextLine().trim());
            testDTO.setSubject(subject);

            boolean isLastQuestion = false;
            while (!isLastQuestion){
                QuestionDTO questionDTO = new QuestionDTO();
                System.out.print("\n\tEnter the question: ");
                questionDTO.setText(console.nextLine().trim());

                boolean isLastAnswer = false;
                while (!isLastAnswer){
                    AnswerDTO answerDTO = new AnswerDTO();
                    System.out.print("\n\t\tEnter the answer: ");
                    answerDTO.setText(console.nextLine().trim());

                    System.out.print("\t\tIs correct answer? (Y/N): ");
                    answerDTO.setCorrect(console.nextLine().trim().toUpperCase().equals("Y"));

                    if (!questionDTO.getAnswerList().add(answerDTO)){
                        System.out.println("This answer has already been added");
                    }

                    System.out.print("\n\t\tNext answer? (Y/N): ");
                    isLastAnswer = console.nextLine().trim().toUpperCase().equals("N");
                }

                if (!testDTO.getQuestionSet().add(questionDTO)){
                    System.out.println("This question has already been added");
                }

                System.out.print("\n\tNext question? (Y/N): ");
                isLastQuestion = console.nextLine().trim().toUpperCase().equals("N");
            }
        }
        catch (NumberFormatException ex){
            throw new ViewException("Incorrect errorThreshold", ex);
        }

        return new RequestWithTestDTO(CommandEnum.ADD_TEST, testDTO);
    }

}
