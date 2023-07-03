package aston.greenteam.eventmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "values")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "parameters_value",
            joinColumns = @JoinColumn(name = "value_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id", referencedColumnName = "id"))
    private Set<Parameter> parameters = new HashSet<>();
}
