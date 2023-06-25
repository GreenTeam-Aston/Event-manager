package aston.greenteam.eventmanager.entities.mock;

import aston.greenteam.eventmanager.entities.Event;
import jakarta.persistence.*;

@Entity
public class EventPhoto implements Mock{

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_event")
    private Event event;
}
