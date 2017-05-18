package app.repository;

import app.model.items.characteristics.Charecteristic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepo extends CrudRepository<Charecteristic, Integer> {

    Iterable<Charecteristic> findAllByCategoriyaId(Integer categoryId);

}
