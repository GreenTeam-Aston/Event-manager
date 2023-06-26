package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
}
