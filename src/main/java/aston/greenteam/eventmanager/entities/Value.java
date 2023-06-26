package aston.greenteam.eventmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "values")
@Data
@NoArgsConstructor
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parameters_value",
            joinColumns = @JoinColumn(name = "value_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id", referencedColumnName = "id"))
    private Set<Parameter> parameters;
}
