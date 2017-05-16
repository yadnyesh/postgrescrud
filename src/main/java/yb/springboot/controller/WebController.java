package yb.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yb.springboot.model.Customer;
import yb.springboot.repo.CustomerRepository;
import yb.springboot.service.CustomerService;

import java.util.List;

/**
 * Created by z063407 on 5/16/17.
 */
@RestController
public class WebController {
    @Autowired
    CustomerRepository repository;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/save")
    public String process(){
        repository.save(new Customer("Jack", "Smith"));
        repository.save(new Customer("Adam", "Johnson"));
        repository.save(new Customer("Kim", "Smith"));
        repository.save(new Customer("David", "Williams"));
        repository.save(new Customer("Peter", "Davis"));
        return "Done";
    }

    @RequestMapping("/findall")
    public String findAll(){
        String result = "<html>";

        for(Customer cust : repository.findAll()){
            result += "<div>" + cust.toString() + "</div>";
        }

        return result + "</html>";
    }

    @RequestMapping("/findbyid")
    public String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findOne(id).toString();
        return result;
    }

    @RequestMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName){
        String result = "<html>";

        for(Customer cust: repository.findByLastName(lastName)){
            result += "<div>" + cust.toString() + "</div>";
        }

        return result + "</html>";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String viewCustomers(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
        String result = "<html>";

        List<Customer> customers = customerService.getPage(pageNumber);

        for (Customer customer : customers) {
            result += customer.toString() + "br/";
        }

        return result + "</html>";
    }
}
