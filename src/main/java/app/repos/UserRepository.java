package app.repos;

import app.entityes.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User,Long> {
    User findByNameAndPassword(String name, String password);

    @Query("SELECT u from User u WHERE u.name LIKE '%name%'")
    List<User> findByName(String name);

}
