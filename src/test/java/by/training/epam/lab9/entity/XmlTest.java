package by.training.epam.lab9.entity;

/**
 * Created by alexh on 28.10.2016.
 */
public class XmlTest {

    private long id;
    private String name;
    private byte errorThreshold;
    private long subjectID;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getErrorThreshold() {
        return errorThreshold;
    }

    public void setErrorThreshold(byte errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    public long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(long subjectID) {
        this.subjectID = subjectID;
    }
}
