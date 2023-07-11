package aston.greenteam.eventmanager.services.impl;


import aston.greenteam.eventmanager.dtos.BucketDTO;
import aston.greenteam.eventmanager.entities.Bucket;
import aston.greenteam.eventmanager.exceptions.ObjectNotFoundException;
import aston.greenteam.eventmanager.mappers.BucketMapper;
import aston.greenteam.eventmanager.repositories.BucketRepository;
import aston.greenteam.eventmanager.repositories.EventRepository;
import aston.greenteam.eventmanager.services.BucketService;
import aston.greenteam.eventmanager.services.EventService;
import aston.greenteam.eventmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;
    private final BucketMapper bucketMapper;
    private final UserService userService;
    private final EventService eventService;
    private final EventRepository eventRepository;

    @Override
    public BucketDTO findById(Long id){
        return bucketMapper.bucketToDTO(bucketRepository.findById(id).orElseThrow());
    }

    @Override
    public List<BucketDTO> findAll(){
        return bucketRepository.findAll()
                .stream()
                .map(bucketMapper::bucketToDTO)
                .toList();
    }

    @Override
    public List<BucketDTO> findAllByUserCreated(Long id){
        return bucketRepository.findAllByUser(id)
                .stream()
                .map(bucketMapper::bucketToDTO)
                .toList();
    }

    @Override
    public List<BucketDTO> findAllByEventId(Long id){
        return bucketRepository.findAllByEventId(id)
                .stream()
                .map(bucketMapper::bucketToDTO)
                .toList();
    }

    @Override
    public void createBucket(BucketDTO bucketDTO, Long userId, Long eventId){
        Bucket bucket = new Bucket();
        bucket.setName(bucketDTO.getName());
        bucket.setPrice(bucketDTO.getPrice());
        bucket.setUsers(List.of(userService.findById(userId)));
        bucket.setEvents(List.of(eventRepository.findById(eventId).orElseThrow(
                () -> new ObjectNotFoundException("Event with id:" + eventId + " not found.")
        )));
        bucketRepository.save(bucket);
    }

    @Override
    public void delete(Long id){
        bucketRepository.deleteById(id);
    }
}