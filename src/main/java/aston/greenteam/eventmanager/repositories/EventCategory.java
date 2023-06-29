package aston.greenteam.eventmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategory extends JpaRepository<EventCategory, Long> {
    //some code
}
