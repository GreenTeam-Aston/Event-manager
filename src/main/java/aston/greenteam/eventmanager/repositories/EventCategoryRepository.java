package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
    @Override
    List<EventCategory> findAll();
    @Override
    Optional<EventCategory> findById(Long id);

    @Override
    <S extends EventCategory> S save(S entity);
}
