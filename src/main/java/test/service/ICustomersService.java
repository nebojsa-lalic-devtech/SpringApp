package test.service;

import test.domain.Customers;

import java.util.Collection;

public interface ICustomersService {

    Customers saveCustomer(Customers costum);
    Boolean deleteCustomer(Long costId);
    Customers editCustomer(Customers costum);
    Customers findCustomer(Long costId);
    Collection<Customers> getAllCustomers();

}
