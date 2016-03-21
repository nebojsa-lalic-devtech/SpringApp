package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.domain.Customers;
import test.repository.ICustomersRepository;

import java.util.Collection;

@Service
@Transactional
public class CustomersService implements ICustomersService{

    @Autowired
    protected ICustomersRepository customersRepository;

    @Override
    public Customers saveCustomer(Customers costum){
        return customersRepository.save(costum);
    }

    @Override
    public Boolean deleteCustomer(Long costId){

        Customers temp = customersRepository.findOne(costId);
        if(temp!=null){
            customersRepository.delete(costId);
            return true;
        }
        return false;
    }

    @Override
    public Collection<Customers> getAllCustomers(){
        Iterable<Customers> itr = customersRepository.findAll();
        return (Collection<Customers>)itr;
    }

    @Override
    public Customers findCustomer(Long costId){
        return customersRepository.findOne(costId);
    }

    @Override
    public Customers editCustomer(Customers costum){
        Customers one = customersRepository.findOne(costum.getId());
        one.setCity(costum.getCity());
        one.setFirstName(costum.getFirstName());
        one.setLastName(costum.getLastName());
        return customersRepository.save(costum);
    }

}
