import java.util.List;

import dao.CustomerDAO;
import database.dbConnect;
import entities.Customer;

public class App {
    public static void main(String[] args) throws Exception {
        CustomerDAO customerDAO = new CustomerDAO();

        Customer newCustomer = new Customer("C002", "John Doe", "1234567890");
        customerDAO.addCustomer(newCustomer);

        // newCustomer.setName("John Smith");
        // customerDAO.updateCustomer(newCustomer);

        // Customer retrievedCustomer = customerDAO.getCustomerById("C001");
        // System.out.println("Retrieved Customer: " + retrievedCustomer.getName());
        //get all Cusomter
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getId() + ", Name: " + customer.getName() + ", Phone: " + customer.getPhoneNumber());
        }
        // customerDAO.deleteCustomer("C001");
    }
}
