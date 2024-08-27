package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Repository.AddressRepository;
import com.example.capstone3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer id,Customer customer) {
        Customer c = customerRepository.findCustomerById(id);
        if (c == null) {
            throw new ApiException("Customer not found");
        }
        c.setName(customer.getName());
        c.setAge(customer.getAge());
        c.setEmail(customer.getEmail());
        c.setHeight(customer.getHeight());
        c.setWeight(customer.getWeight());
        c.setPhone(customer.getPhone());
        customerRepository.save(c);
    }

    public void deleteCustomer(Integer id) {
        Customer c = customerRepository.findCustomerById(id);
        if (c == null) {
            throw new ApiException("Customer not found");
        }
        customerRepository.delete(c);
    }


}