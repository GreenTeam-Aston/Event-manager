package aston.greenteam.eventmanager.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "events_photo")
public class EventPhoto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_event")
    private Event event;
}
