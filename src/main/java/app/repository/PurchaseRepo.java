package app.repository;


import app.model.items.Purchase;
import app.model.items.PurchaseState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepo extends CrudRepository<Purchase, Integer> {

    Purchase findByCustomerBoughtPhoneAndStateAndId(String custPhone, PurchaseState state, int id);

    Iterable<Purchase> findTop20ByCustomerBoughtPhoneOrderByDateChangedAsc(String customerPhone);

    Iterable<Purchase> findTop20ByStateOrderByDateChanged(PurchaseState state);

}
