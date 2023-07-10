package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.BucketDTO;

import java.util.List;

public interface BucketService {

    BucketDTO findById(Long id);

    List<BucketDTO> findAll();

    List<BucketDTO> findAllByUserCreated(Long idUserCreated);

    List<BucketDTO> findAllByEventId(Long idEvent);

    void createBucket(BucketDTO bucketDTO, Long userCreated, Long eventId);

    void delete(Long id);
}
