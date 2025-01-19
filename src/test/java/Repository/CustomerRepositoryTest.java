package Repository;

import Model.Customer.Customer;
import Model.Customer.CustomerType;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

public class CustomerRepositoryTest extends TestCase {
        CustomerRepository customerRepository = new CustomerRepository("C:\\Users\\User\\IdeaProjects\\Team-Project-\\src\\test\\resources\\customersId_test.txt", "C:\\Users\\User\\IdeaProjects\\Team-Project-\\src\\test" +
                "\\resources\\customers_test.txt");


    public void testAdd() {
        //given
        Customer customer = new Customer(null,"Gleb", CustomerType.NEW);
        //when
        Assertions.assertEquals(customer,customerRepository.add(customer));
        //then

    }

}