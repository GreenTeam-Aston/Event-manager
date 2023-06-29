package aston.greenteam.eventmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories_for_events")
@Data
@NoArgsConstructor
public class EventCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(name = "events_categories",
            joinColumns = @JoinColumn(name = "id_category"),
            inverseJoinColumns =@JoinColumn(name = "id_event")
    )
    private List<Event> events;
}
