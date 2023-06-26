package aston.greenteam.eventmanager.repositories;

import aston.greenteam.eventmanager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository <Product, Long> {
}
