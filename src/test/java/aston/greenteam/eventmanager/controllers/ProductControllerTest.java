package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.ProductDTO;
import aston.greenteam.eventmanager.entities.Product;
import aston.greenteam.eventmanager.services.impl.ProductsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ProductsServiceImpl productsServiceImpl;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders.
                standaloneSetup(new ProductController(productsServiceImpl)).build();
    }

    @Test
    void findByValueId() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        when(productsServiceImpl.findById(1L)).thenReturn(productDTO);
        mockMvc.perform(get("/api/v1/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void findAll() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        List<ProductDTO> parameterDTOList = new ArrayList<>();
        parameterDTOList.add(productDTO);
        when(productsServiceImpl.findAll()).thenReturn(parameterDTOList);
        mockMvc.perform(get("/api/v1/product"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setTitle("test");

        when(productsServiceImpl.createProduct(any(ProductDTO.class))).thenReturn(productDTO);
        mockMvc.perform(post("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("test"));
    }

    @Test
    void update() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setTitle("updateProduct");

        when(productsServiceImpl.updateProduct(any(Product.class))).thenReturn(productDTO);
        mockMvc.perform(put("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("updateProduct"));
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(productsServiceImpl).deleteProduct(anyLong());

        mockMvc.perform(delete("/api/v1/product/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void assignParameterToProduct() {
        Long productId = 1L;
        Long parameterId = 2L;

        when(productsServiceImpl.assignParameterToProduct(productId, parameterId)).thenReturn(new ProductDTO());
        ProductDTO productDTO = productsServiceImpl.assignParameterToProduct(productId, parameterId);
        assertEquals(new ProductDTO(), productDTO);
    }
}