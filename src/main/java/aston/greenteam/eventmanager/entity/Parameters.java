package aston.greenteam.eventmanager.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "parameters")
public class Parameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_parameters",
            joinColumns = @JoinColumn(name = "parameter_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Products> products = new HashSet<Products>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parameters_value",
            joinColumns = @JoinColumn(name = "parameter_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "value_id", referencedColumnName = "id"))
    private Set<Values> values = new HashSet<Values>();

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

    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }

    public Set<Values> getValues() {
        return values;
    }

    public void setValues(Set<Values> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(products, that.products) && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products, values);
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                ", values=" + values +
                '}';
    }
}
