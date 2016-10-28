package by.training.epam.lab9;

import by.training.epam.lab9.bean.request.Request;
import by.training.epam.lab9.bean.response.Response;
import by.training.epam.lab9.controller.Controller;
import by.training.epam.lab9.dao.exception.DAOException;
import by.training.epam.lab9.view.View;
import by.training.epam.lab9.view.exception.ViewException;
import by.training.epam.lab9.view.factory.ViewFactory;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 10/25/2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, DAOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ViewFactory viewFactory = new ViewFactory();
        Controller controller = new Controller();

        while (true){
            try {
                System.out.print("\nEnter the command: ");
                CommandEnum commandEnum = CommandEnum
                        .getEnum(scanner.nextLine().trim().toUpperCase());
                View view = viewFactory.getView(commandEnum);
                Request request = view.getRequest();
                Response response = controller.doRequest(request);
                view.showResponse(response);
            }
            catch (EnumConstantNotPresentException ex){
                System.out.println("Error: Incorrect command");
            }
            catch (ViewException ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}
