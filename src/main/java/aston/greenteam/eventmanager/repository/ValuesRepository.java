package aston.greenteam.eventmanager.repository;

import aston.greenteam.eventmanager.entity.Values;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValuesRepository extends JpaRepository <Values, Long> {
}
