package aston.greenteam.eventmanager.entities;

import aston.greenteam.eventmanager.entities.mock.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @Column(name = "end_datetime")
    private LocalDateTime endDatetime;

    @Column(name = "link_event_site")
    private String linkEventSite;

    @Column(name = "link_image")
    private String linkImage;

    @Column(name = "price")
    private Integer price;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "id_user_created")
    private Integer idUserCreated;

    @Column(name = "tags")
    private String tags;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private Collection<Notice> notices;

    @ManyToMany
    @JoinTable(name = "events_categories",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns =@JoinColumn(name = "id_category")
    )
    private Collection<EventCategory> eventCategories;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private Collection<EventPhoto> eventPhotos;

    @ManyToMany
    @JoinTable(name = "user_events",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns =@JoinColumn(name = "id_user")
    )
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "event_buckets",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns =@JoinColumn(name = "id_bucket")
    )
    private Collection<Bucket> buckets;

}
