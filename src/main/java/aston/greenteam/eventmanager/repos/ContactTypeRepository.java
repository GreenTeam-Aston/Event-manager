package aston.greenteam.eventmanager.repos;

import aston.greenteam.eventmanager.entities.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
}
