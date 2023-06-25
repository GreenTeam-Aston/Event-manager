package aston.greenteam.eventmanager.entities.mock;

import aston.greenteam.eventmanager.entities.Event;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Bucket implements Mock{

    @Id
    private Long id;
    @ManyToMany
    @JoinTable(name = "event_buckets",
            joinColumns = @JoinColumn(name = "id_bucket"),
            inverseJoinColumns =@JoinColumn(name = "id_event")
    )
    private Collection<Event> eventCollection;
}
