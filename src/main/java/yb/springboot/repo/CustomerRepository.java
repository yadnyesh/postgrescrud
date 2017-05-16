package yb.springboot.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import yb.springboot.model.Customer;

import java.util.List;

/**
 * Created by z063407 on 5/16/17.
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
}