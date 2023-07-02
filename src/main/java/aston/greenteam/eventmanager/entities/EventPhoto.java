package aston.greenteam.eventmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events_photo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventPhoto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_event")
    private Event event;
}