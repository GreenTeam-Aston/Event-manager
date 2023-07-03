package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.dtos.ProductDTO;
import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import aston.greenteam.eventmanager.entities.Product;
import aston.greenteam.eventmanager.entities.Value;
import aston.greenteam.eventmanager.exceptions.MyEntityNotFoundException;
import aston.greenteam.eventmanager.mappers.ProductMapper;
import aston.greenteam.eventmanager.mappers.ValueMapper;
import aston.greenteam.eventmanager.repositories.ParametersRepository;
import aston.greenteam.eventmanager.repositories.ProductsRepository;
import aston.greenteam.eventmanager.repositories.ValuesRepository;
import aston.greenteam.eventmanager.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    private final ParametersRepository parametersRepository;

    private final ProductMapper mapper;

    @Override
    public ProductDTO findById(long id) {
        return productsRepository.findById(id)
                .map(mapper::productToProductDTO)
                .orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    @Override
    public List<ProductDTO> findAll() {
        return productsRepository.findAll()
                .stream()
                .map(product -> {
                    ProductDTO productDTO = mapper.productToProductDTO(product);
                    return productDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product newProduct=new Product();
        newProduct.setTitle(productDTO.getTitle());
        productsRepository.save(newProduct);
        return mapper.productToProductDTO(newProduct);
    }
    @Override
    public ProductDTO updateProduct(Product product) {
        productsRepository.save(product);
        return mapper.productToProductDTO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }


    @Override
    public long productsCount() {
        return 0;
    }

    @Override
    public List<ProductDTO> findAllByParameterId(long parameterId) {
        return null;
    }

    @Override
    public ProductDTO assignParameterToProduct(Long productId, Long parameterId) {
        Set<Parameter> parameterSet=null;
        Product product = productsRepository.findById(productId).get();
        Parameter parameter = parametersRepository.findById(parameterId).get();
        parameterSet = product.getParameters();
        parameterSet.add(parameter);
        product.setParameters(parameterSet);
        productsRepository.save(product);
        return mapper.productToProductDTO(product);
    }

}
