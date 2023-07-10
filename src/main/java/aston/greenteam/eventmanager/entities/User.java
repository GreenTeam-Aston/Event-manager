package aston.greenteam.eventmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String login;

    private String password;

    private String nickname;

    private Integer age;

    private String gender;

    @ManyToOne
    @JoinColumn(name = "user_role")
    private UserRole userRole;

    @ManyToMany
    @JoinTable(name = "user_events",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns =@JoinColumn(name = "id_role")
    )
    private List<Role> roles;

    @ManyToMany
    @JoinTable(name = "user_events",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns =@JoinColumn(name = "id_event")
    )
    private List<Event> events;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Event> myEvents;

    @ManyToMany
    @JoinTable(name = "user_buckets",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns =@JoinColumn(name = "id_bucket")
    )
    private List<Bucket> bucket;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @ManyToMany
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns =@JoinColumn(name = "id_friend")
    )
    private List<User> friends;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(nickname, user.nickname) && Objects.equals(age, user.age) && Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, nickname, age, gender);
    }
}
