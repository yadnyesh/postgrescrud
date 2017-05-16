package yb.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yb.springboot.model.Customer;
import yb.springboot.repo.CustomerRepository;


@Service
public class CustomerService {

    private final static int PAGESIZE = 2;

    @Autowired
    CustomerRepository repository;

    public void save(Customer customer) {
        repository.save(customer);
    }

    public Iterable<Customer> findAllCustomers() {
        return repository.findAll();
    }

    public List<Customer> getPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");

        return repository.findAll(request).getContent();
    }
}
