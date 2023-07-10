package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.dtos.BucketDTO;
import aston.greenteam.eventmanager.services.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/buckets")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService bucketService;

    @GetMapping
    public List<BucketDTO> getAllBuckets() {
        return bucketService.findAll();
    }

    @GetMapping("/{id}")
    public BucketDTO getById(@PathVariable Long id) {
        return bucketService.findById(id);
    }

    @GetMapping("/events/{id}")
    public List<BucketDTO> getAllBucketsByEventId(@PathVariable Long id) {
        return bucketService.findAllByEventId(id);
    }

    @GetMapping("/users/{id}")
    public List<BucketDTO> getAllBucketsByUserId(@PathVariable Long id) {
        return bucketService.findAllByUserCreated(id);
    }

    @PostMapping("/users/{userId}/events/{eventId}")
    public ResponseEntity<?> createBucket(@RequestBody BucketDTO bucketDTO,
                                         @PathVariable Long userId,
                                         @PathVariable Long eventId) {
        bucketService.createBucket(bucketDTO, userId, eventId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeBucket(@PathVariable Long id) {
        bucketService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
