package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.ProductDTO;
import aston.greenteam.eventmanager.entities.Product;
import aston.greenteam.eventmanager.services.impl.ProductsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductsServiceImpl productsServiceImpl;

    //TODO
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findByValueId(@PathVariable Long id) {
        return new ResponseEntity<>(productsServiceImpl.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return new ResponseEntity<>(productsServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productsServiceImpl.createProduct(productDTO);
    }

    @PutMapping
    public ProductDTO update(@RequestBody Product product) {
        return productsServiceImpl.updateProduct(product);
    }

    //TODO refactor
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        productsServiceImpl.deleteProduct(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{productId}/parameter/{parameterId}")
    public ProductDTO assignProjectToEmployee(@PathVariable Long productId,
                                              @PathVariable Long parameterId) {
        return productsServiceImpl.assignParameterToProduct(productId, parameterId);
    }
}