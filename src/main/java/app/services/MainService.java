package app.services;


import app.entityes.*;
import app.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    InvoiceIncomeRepository invoiceIncomeRepository;
    @Autowired
    InvoicePropertyRepo invoicePropertyRepo;
    @Autowired
    InvoiceTransportRepo invoiceTransportRepo;
    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    TransportRepo transportRepo;

    public User login(String name,String password) {
        return userRepository.findByNameAndPassword(name,password);
    }

    public void saveInvoiceProperty(InvoiceProperty invoiceProperty) {
        invoicePropertyRepo.save(invoiceProperty);
    }

    public void saveInvoceTransport(InvoiceTransport invoiceTransport) {
        invoiceTransportRepo.save(invoiceTransport);
    }

    public void saveInvoceIncome(InvoiceIncome invoiceIncome) {
        invoiceIncomeRepository.save(invoiceIncome);
    }

    public void saveIncome(Income income) {
        incomeRepository.save(income);
    }

    public void saveProperty(Property property) {
        propertyRepo.save(property);
    }

    public void saveTransport(Transport transport) {
        transportRepo.save(transport);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> search(String name) {
        return  userRepository.findByName(name);
    }

    public InvoiceIncome getInvoiceIncome(Long invoiceId) {
        return invoiceIncomeRepository.findById(invoiceId).get();
    }

    public InvoiceProperty getInvoiceProperty(Long invoiceId) {
        return invoicePropertyRepo.findById(invoiceId).get();
    }

    public InvoiceTransport getInvoiceTransport(Long invoiceId) {
        return invoiceTransportRepo.findById(invoiceId).get();
    }

    public Income getIncome(Long incomeId) {
        return incomeRepository.findById(incomeId).get();
    }

    public Property getProperty(Long id) {
        return propertyRepo.findById(id).get();
    }

    public Transport getTransport(Long id) {
        return transportRepo.findById(id).get();
    }
}
