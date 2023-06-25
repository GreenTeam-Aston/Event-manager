package aston.greenteam.eventmanager.entities.mock;

import aston.greenteam.eventmanager.entities.Event;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class User implements Mock{

    @Id
    private Long id;
    @ManyToMany
    @JoinTable(name = "user_events",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns =@JoinColumn(name = "id_event")
    )
    private Collection<Event> eventCollection;
}
