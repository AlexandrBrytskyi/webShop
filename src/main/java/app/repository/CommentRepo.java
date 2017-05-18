package app.repository;

import app.model.items.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepo extends CrudRepository<Comment, Integer> {

}
