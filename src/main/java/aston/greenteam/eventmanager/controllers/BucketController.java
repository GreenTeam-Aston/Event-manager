package aston.greenteam.eventmanager.controllers;


import aston.greenteam.eventmanager.dtos.BucketDTO;
import aston.greenteam.eventmanager.services.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buckets")
@RequiredArgsConstructor
public class BucketController {

    private final BucketService bucketService;

    @GetMapping("/get-all")
    public List<BucketDTO> getAllBuckets() {
        return bucketService.findAll();
    }

    @GetMapping("/get-by-id/{id}")
    public BucketDTO getById(@PathVariable Long id) {
        return bucketService.findById(id);
    }

    @GetMapping("/get-all-by-event/{id}")
    public List<BucketDTO> getAllBucketsByEventId(@PathVariable Long id) {
        return bucketService.findAllByEventId(id);
    }

    @GetMapping("/get-all-by-user/{id}")
    public List<BucketDTO> getAllBucketsByUserId(@PathVariable Long id) {
        return bucketService.findAllByUserCreated(id);
    }

    @PostMapping("/create-bucket/{userId}/{eventId}")
    public ResponseEntity<?> createBucket(@RequestBody BucketDTO bucketDTO,
                                         @PathVariable Long userId,
                                         @PathVariable Long eventId) {
        bucketService.createBucket(bucketDTO, userId, eventId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/remove-bucket/{id}")
    public ResponseEntity<?> removeBucket(@PathVariable Long id) {
        bucketService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
