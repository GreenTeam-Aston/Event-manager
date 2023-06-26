package aston.greenteam.eventmanager.repos;

import aston.greenteam.eventmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
