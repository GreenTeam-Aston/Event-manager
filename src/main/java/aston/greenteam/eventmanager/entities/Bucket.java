package aston.greenteam.eventmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "buckets")
@Data
@NoArgsConstructor
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @ManyToMany
    @JoinTable(name = "product_buckets",
            joinColumns = @JoinColumn(name = "id_bucket"),
            inverseJoinColumns =@JoinColumn(name = "id_product")
    )
    private List<Product> products;

    @ManyToMany
    @JoinTable(name = "event_buckets",
            joinColumns = @JoinColumn(name = "id_bucket"),
            inverseJoinColumns =@JoinColumn(name = "id_event")
    )
    private List<Event> events;

    @ManyToMany
    @JoinTable(name = "user_buckets",
            joinColumns = @JoinColumn(name = "id_bucket"),
            inverseJoinColumns =@JoinColumn(name = "id_user")
    )
    private List<User> users;
}
