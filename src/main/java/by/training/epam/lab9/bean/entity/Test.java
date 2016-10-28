package by.training.epam.lab9.bean.entity;

/**
 * Created by alexh on 26.10.2016.
 */
public class Test {

    private Long id;
    private String name;
    private Byte errorThreshold;
    private Subject subject;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getErrorThreshold() {
        return errorThreshold;
    }

    public void setErrorThreshold(Byte errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (id != null ? !id.equals(test.id) : test.id != null) return false;
        if (name != null ? !name.equals(test.name) : test.name != null) return false;
        if (errorThreshold != null ? !errorThreshold.equals(test.errorThreshold) : test.errorThreshold != null)
            return false;
        return subject != null ? subject.equals(test.subject) : test.subject == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (errorThreshold != null ? errorThreshold.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }
}
