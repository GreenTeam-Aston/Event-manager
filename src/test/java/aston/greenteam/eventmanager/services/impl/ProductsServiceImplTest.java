package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.ProductDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import aston.greenteam.eventmanager.entities.Product;
import aston.greenteam.eventmanager.mappers.ProductMapper;
import aston.greenteam.eventmanager.repositories.ParametersRepository;
import aston.greenteam.eventmanager.repositories.ProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsServiceImplTest {

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private ParametersRepository parametersRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductsServiceImpl productsServiceImpl;

    private ProductDTO productDTO;

    private Product product;

    private Parameter parameter;

    @BeforeEach
    public void setUp() {

        product = new Product();
        product.setId(1L);
        product.setTitle("product");

        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setTitle("productDTO");

        parameter = new Parameter();
        parameter.setId(2L);
        parameter.setName("parameter");

    }

    @Test
    public void testFindById() {
        productsRepository.findById(product.getId());
        Mockito.verify(productsRepository).findById(product.getId());
    }

    @Test
    public void testFindAll() {
        List<Product> products = new ArrayList<>();
        products.add(product);

        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS.add(productDTO);

        when(productsRepository.findAll()).thenReturn(products);
        when(productMapper.productToProductDTO(product)).thenReturn(productDTO);

        List<ProductDTO> results = productsServiceImpl.findAll();

        Assertions.assertEquals(results.size(), 1);
        Assertions.assertEquals(results.get(0).getId(), productDTOS.get(0).getId());
        Assertions.assertEquals(results.get(0).getTitle(), productDTOS.get(0).getTitle());
    }

    @Test
    void createProduct() {
        when(productMapper.productToProductDTO(any(Product.class))).thenReturn(productDTO);
        when(productsRepository.save(any(Product.class))).thenReturn(product);
        ProductDTO result = productsServiceImpl.createProduct(productDTO);
        Assertions.assertEquals(result.getId(), productDTO.getId());
        Assertions.assertEquals(result.getTitle(), productDTO.getTitle());
    }

    @Test
    void updateProduct() {
        when(productMapper.productToProductDTO(product)).thenReturn(productDTO);
        when(productsRepository.save(product)).thenReturn(product);

        ProductDTO result = productsServiceImpl.updateProduct(product);

        Assertions.assertEquals(result.getId(), productDTO.getId());
        Assertions.assertEquals(result.getTitle(), productDTO.getTitle());
    }

    @Test
    void deleteProduct() {
        doNothing().when(productsRepository).deleteById(anyLong());

        productsServiceImpl.deleteProduct(1L);
    }

    @Test
    void assignParameterToProduct() {
        Set<Parameter> parameters = new HashSet<>();
        parameters.add(parameter);

        when(productsRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(parametersRepository.findById(anyLong())).thenReturn(Optional.of(parameter));

        when(productMapper.productToProductDTO(product)).thenReturn(productDTO);
        product.setParameters(parameters);


        ProductDTO result = productsServiceImpl.assignParameterToProduct(1L, 2L);

        Assertions.assertEquals(result.getId(), productDTO.getId());
        Assertions.assertEquals(result.getTitle(), productDTO.getTitle());
    }
}