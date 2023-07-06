package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.ProductDTO;
import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Product;
import aston.greenteam.eventmanager.entities.Value;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class); //for test

    ProductDTO productToProductDTO(Product product);

    //Product productDTOToProduct(ProductDTO productDTO);

}

//package aston.greenteam.eventmanager.mappers;
//
//import aston.greenteam.eventmanager.dtos.ParameterDTO;
//import aston.greenteam.eventmanager.dtos.ProductDTO;
//import aston.greenteam.eventmanager.dtos.ValueDTO;
//import aston.greenteam.eventmanager.entities.Parameter;
//import aston.greenteam.eventmanager.entities.Product;
//import aston.greenteam.eventmanager.entities.Value;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ProductMapper {
//
//    public ProductDTO productToProductDTO(Product product) {
//        return ProductDTO.builder()
//                .id(product.getId())
//                .title(product.getTitle())
//                .description(product.getDescription())
//                .image(product.getImage())
//                .price(product.getPrice())
//                .quantity(product.getQuantity())
//                .build();
//    }
//
//}
