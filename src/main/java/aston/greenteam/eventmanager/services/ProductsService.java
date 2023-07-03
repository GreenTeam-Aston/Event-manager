package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.dtos.ProductDTO;
import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Product;
import aston.greenteam.eventmanager.entities.Value;

import java.util.List;

public interface ProductsService {

    ProductDTO findById(long id);
    List<ProductDTO> findAll();

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct (Product product);

    void deleteProduct(Long id);

    long productsCount();

    List<ProductDTO> findAllByParameterId(long parameterId);
    ProductDTO assignParameterToProduct(Long productId, Long parameterId);
}
