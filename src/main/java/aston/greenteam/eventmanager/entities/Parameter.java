package aston.greenteam.eventmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "parameters")
@Data
@NoArgsConstructor
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_parameters",
            joinColumns = @JoinColumn(name = "parameter_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parameters_value",
            joinColumns = @JoinColumn(name = "parameter_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "value_id", referencedColumnName = "id"))
    private Set<Value> values;
}
