package aston.greenteam.eventmanager.repos;

import aston.greenteam.eventmanager.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
