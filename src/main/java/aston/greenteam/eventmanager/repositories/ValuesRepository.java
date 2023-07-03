package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValuesRepository extends JpaRepository <Value, Long> {

    Value findValueByName (String name);

}
