package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.BucketDTO;
import aston.greenteam.eventmanager.entities.Bucket;
import org.springframework.stereotype.Component;

@Component
public class BucketMapper {

    public BucketDTO bucketToDTO(Bucket bucket){
        return BucketDTO.builder()
                .id(bucket.getId())
                .name(bucket.getName())
                .price(bucket.getPrice())
                .build();
    }
}
