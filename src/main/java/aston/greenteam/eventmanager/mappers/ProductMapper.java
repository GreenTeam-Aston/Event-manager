package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.dtos.ProductDTO;
import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import aston.greenteam.eventmanager.entities.Product;
import aston.greenteam.eventmanager.entities.Value;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO productToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .image(product.getImage())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
//    public ParameterDTO parameterToParameterDTO(Parameter parameter) {
//        return ParameterDTO.builder()
//                .id(parameter.getId())
//                .name(parameter.getName())
//                .products(parameter.getProducts())
//                .values(parameter.getValues())
//                .build();
//    }
}
