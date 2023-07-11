package aston.greenteam.eventmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "events_photo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "photo_uri")
    private String photoUri;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;
}