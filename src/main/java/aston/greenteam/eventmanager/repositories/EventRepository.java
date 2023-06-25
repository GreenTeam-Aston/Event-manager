package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
}
