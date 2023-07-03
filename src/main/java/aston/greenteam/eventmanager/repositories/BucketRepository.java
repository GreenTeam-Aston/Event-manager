package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.Bucket;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {

    Optional<Bucket> findById(Long id);
    List<Bucket> findAll();

    @Query(value = "select * from user_buckets ub join buckets b on ub.id_bucket  = b.id where ub.id_user  = :userId", nativeQuery = true)
    List<Bucket> findAllByUser(Long userId);

    @Query(value = "select * from event_buckets eb join buckets b on eb.id_bucket  = b.id where eb.id_event  = :eventId", nativeQuery = true)
    List<Bucket> findAllByEvent(Long eventId);




}
