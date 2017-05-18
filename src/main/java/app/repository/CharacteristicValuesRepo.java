package app.repository;


import app.model.items.characteristics.CharacteristicValue;
import org.springframework.data.repository.CrudRepository;

public interface CharacteristicValuesRepo  extends CrudRepository<CharacteristicValue, Integer>{

    Iterable<CharacteristicValue> findAllByCharecteristicIdOrderByCharecteristicNameAsc(int characterId);
}
