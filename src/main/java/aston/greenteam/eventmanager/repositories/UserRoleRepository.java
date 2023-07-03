package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByUserRole(String name);
}
