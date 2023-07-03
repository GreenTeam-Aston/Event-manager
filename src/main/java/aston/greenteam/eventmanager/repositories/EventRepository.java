package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {

    Optional<Event> findById(Long id);
    List<Event> findAll();
    List<Event> findAllByUser(User user);
    List<Event> findAllByTagsIgnoreCase(String tag);
}
