package aston.greenteam.eventmanager.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "values")
public class Values {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parameters_value",
            joinColumns = @JoinColumn(name = "value_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id", referencedColumnName = "id"))
    private Set<Parameters> parameters = new HashSet<Parameters>();

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

    public Set<Parameters> getParameters() {
        return parameters;
    }

    public void setParameters(Set<Parameters> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Values values = (Values) o;
        return id == values.id && Objects.equals(name, values.name) && Objects.equals(parameters, values.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parameters);
    }

    @Override
    public String toString() {
        return "Values{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
