package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {

    @Query(value = "select * from user_buckets ub join buckets b on ub.id_bucket  = b.id where ub.id_user  = :id", nativeQuery = true)
    List<Bucket> findAllByUser(Long id);

    @Query(value = "select * from event_buckets eb join buckets b on eb.id_bucket  = b.id where eb.id_event  = :id", nativeQuery = true)
    List<Bucket> findAllByEventId(Long id);
}
