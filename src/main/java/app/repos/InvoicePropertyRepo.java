package app.repos;

import app.entityes.InvoiceProperty;
import org.springframework.data.repository.CrudRepository;

public interface InvoicePropertyRepo extends CrudRepository<InvoiceProperty,Long> {
}
