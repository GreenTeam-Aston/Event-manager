package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersRepository extends JpaRepository<Parameter, Long> {

    Parameter findByName(String name);
}
