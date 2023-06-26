package aston.greenteam.eventmanager.repository;

import aston.greenteam.eventmanager.entity.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametersRepository extends JpaRepository<Parameters, Long> {
}
