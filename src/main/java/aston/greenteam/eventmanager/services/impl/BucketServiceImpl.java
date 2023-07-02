package aston.greenteam.eventmanager.services.impl;


import aston.greenteam.eventmanager.dtos.BucketDTO;
import aston.greenteam.eventmanager.entities.Bucket;
import aston.greenteam.eventmanager.entities.Event;
import aston.greenteam.eventmanager.entities.User;
import aston.greenteam.eventmanager.repositories.BucketRepository;
import aston.greenteam.eventmanager.services.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;

    public Bucket findById(Long id){
        return bucketRepository.findById(id).orElseThrow();

    }

    public List<Bucket> findAll(){
        return bucketRepository.findAll();
    }

    public List<Bucket> findAllByUserCreated(Long idUser){
        return bucketRepository.findAllByUser(idUser);
    }

    public List<Bucket> findAllByEvent(Long idEvent){
        return bucketRepository.findAllByEvent(idEvent);
    }

    public void createBucket(BucketDTO bucketDTO, Long userCreated, Long eventId){
        User user = new User();
        user.setId(userCreated);
        Event event = new Event();
        event.setId(eventId);

        Bucket bucket = new Bucket();
        bucket.setName(bucketDTO.getName());
        bucket.setPrice(bucketDTO.getPrice());
        bucket.setUsers(List.of(user));
        bucket.setEvents(List.of(event));

        bucketRepository.save(bucket);
    }

    public void delete(Long id){
        Bucket bucket = new Bucket();
        bucket.setId(id);
        bucketRepository.delete(bucket);
    }




    public BucketDTO bucketToDTO(Bucket bucket){
        return BucketDTO.builder()
                .id(bucket.getId())
                .name(bucket.getName())
                .price(bucket.getPrice())
                .build();
    }

}
