package aston.greenteam.eventmanager.repository;

import aston.greenteam.eventmanager.entity.NoticeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeCategoryRepository extends JpaRepository<NoticeCategory, Long> {
}
