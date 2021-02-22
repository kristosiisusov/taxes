package app.repos;

import app.entityes.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepo extends CrudRepository<Property,Long> {
}
