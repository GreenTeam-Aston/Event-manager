package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.NoticeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeCategoryRepository extends JpaRepository<NoticeCategory, Long> {
}
