package by.training.epam.lab9.bean.entity;

/**
 * Created by Aliaksandr_Harmaza on 10/25/2016.
 */
public class Subject {

    private Long id;
    private String name;


    public Subject(){}

    public Subject(Long id, String name){
        setId(id);
        setName(name);
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (id != null ? !id.equals(subject.id) : subject.id != null) return false;
        return name != null ? name.equals(subject.name) : subject.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
