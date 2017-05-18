package app.repository;


import app.model.items.Categoriya;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<Categoriya, Integer> {

    Iterable<Categoriya> findAllByParentIsNull();

    Iterable<Categoriya> findAllByParentIsNotNull();
}
