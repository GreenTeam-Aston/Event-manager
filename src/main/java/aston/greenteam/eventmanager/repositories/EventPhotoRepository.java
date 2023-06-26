package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.EventPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPhotoRepository extends JpaRepository<EventPhoto, Long> {
}
