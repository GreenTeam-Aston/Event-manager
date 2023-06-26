package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
