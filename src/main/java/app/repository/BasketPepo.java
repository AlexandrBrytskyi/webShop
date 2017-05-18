package app.repository;


import app.model.items.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketPepo extends CrudRepository<Basket, Integer> {

}
