package aston.greenteam.eventmanager.entities.mock;

import aston.greenteam.eventmanager.entities.Event;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class EventCategory implements Mock{

    @Id
    private Long id;
    @ManyToMany
    @JoinTable(name = "events_categories",
            joinColumns = @JoinColumn(name = "id_category"),
            inverseJoinColumns =@JoinColumn(name = "id_event")
    )
    private Collection<Event> eventCollection;
}
