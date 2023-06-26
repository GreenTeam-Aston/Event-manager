package aston.greenteam.eventmanager.entities.mock;

import aston.greenteam.eventmanager.entities.Event;
import jakarta.persistence.*;

@Entity
public class Notice implements Mock {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;
}
