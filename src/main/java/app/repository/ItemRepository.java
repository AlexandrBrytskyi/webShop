package app.repository;


import app.model.items.Item;


import app.model.items.characteristics.CharacteristicValue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {


    List<Item> findAllByCategoriyaIdAndAmountAvailableGreaterThanOrderByPriceAsc(int categoriyaId, int amount, Pageable pageable);

    List<Item> findAllByCategoriyaIdAndAmountAvailableGreaterThanOrderByPriceDesc(int categoriyaId, int amount, Pageable pageable);

    List<Item> findAllByAmountAvailableGreaterThanAndCharacteristicValuesInOrderByPriceAsc(int amount, List<CharacteristicValue> characteristicValues, Pageable pageable);

    List<Item> findAllByAmountAvailableGreaterThanAndCharacteristicValuesInOrderByPriceDesc(int amount, List<CharacteristicValue> characteristicValues, Pageable pageable);
}
