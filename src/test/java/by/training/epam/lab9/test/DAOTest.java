package by.training.epam.lab9.test;

import by.training.epam.lab9.dao.pool.ConnectionPool;
import org.testng.Assert;

/**
 * Created by alexh on 27.10.2016.
 */
public class DAOTest extends Assert {

    private ConnectionPool.Connection connection;


    public DAOTest(){
        try {
            connection = ConnectionPool.getInstance().takeConnection();
        }
        catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
    }


    public ConnectionPool.Connection getConnection(){
        return connection;
    }

}
