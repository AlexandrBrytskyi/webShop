package app.repository;

import app.model.items.ItemAmountEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAmountEntryRepo extends CrudRepository<ItemAmountEntry, Integer> {


}
