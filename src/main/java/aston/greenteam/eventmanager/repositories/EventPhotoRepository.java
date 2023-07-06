package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.EventPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventPhotoRepository extends JpaRepository<EventPhoto, Long> {

    List<EventPhoto> findAllByEvent(Event event);
}
