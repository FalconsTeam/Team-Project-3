package Service;

import Model.Customer.Customer;
import Model.Customer.CustomerType;
import Repository.CustomerRepository;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CustomerServiceTest extends TestCase {
    CustomerRepository customerRepository = new CustomerRepository("C:\\Users\\User\\IdeaProjects\\Team-Project-\\src\\test\\resources\\customersId_test.txt", "C:\\Users\\User\\IdeaProjects\\Team-Project-\\src\\test" +
            "\\resources\\customers_test.txt");
    CustomerService customerService = new CustomerService(customerRepository);

    public void testAddCustomer() {
        //given
        Customer customer = new Customer(null, "Oleg", CustomerType.VIP);

        //when
        customer.setId(customerRepository.setId().getLast().getId() + 1);

        //then
        Assertions.assertEquals(customer, customerService.addCustomer("Oleg", CustomerType.VIP));
    }

    public void testGetAllCustomers() {

        Path path = Path.of("C:\\Users\\User\\IdeaProjects\\Team-Project-\\src\\test\\resources\\customers_test.txt");

        try {
            List<String> customersList = Files.readAllLines(path);
            Assertions.assertEquals(customersList,customerService.getAllCustomers());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}