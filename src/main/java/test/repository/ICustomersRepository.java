package test.repository;

import org.springframework.data.repository.CrudRepository;
import test.domain.Customers;

import java.util.List;

public interface ICustomersRepository extends CrudRepository<Customers, Long> {


}
