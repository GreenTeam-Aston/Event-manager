package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.BucketDTO;
import aston.greenteam.eventmanager.entities.Bucket;

import java.util.List;

public interface BucketService {

    Bucket findById(Long id);
    List<Bucket> findAll();
    List<Bucket> findAllByUserCreated(Long idUserCreated);
    List<Bucket> findAllByEvent(Long idEvent);
    BucketDTO bucketToDTO(Bucket bucket);
    void createBucket(BucketDTO bucketDTO, Long userCreated, Long eventId);
    void delete(Long id);

}
