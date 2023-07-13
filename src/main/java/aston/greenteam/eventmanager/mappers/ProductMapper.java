package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.ProductDTO;
import aston.greenteam.eventmanager.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class); //for test

    ProductDTO productToProductDTO(Product product);
}


