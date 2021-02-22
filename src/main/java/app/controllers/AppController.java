package app.controllers;

import app.Main;
import app.dto.InvoiceDTO;
import app.entityes.*;
import app.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@CrossOrigin("localhost:8080")
public class AppController {

    @Autowired
    MainService mainService;

    @GetMapping("/")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/login")
    public String login(@RequestParam String name,@RequestParam String password) {
        Main.user = mainService.login(name,password);
        if (Main.user == null) {
            return "error";
        } else if (Main.user.isAdmin()){
            return "adminStartPage";
        } else {
            return "startPage";
        }
    }

    @GetMapping("/startPage")
    public String start() {
        if (Main.user == null) {
            return "error";
        } else if (Main.user.isAdmin()){
            return "adminStartPage";
        } else {
            return "startPage";
        }
    }

    @GetMapping("/user")
    public String getUser(ModelMap model,@RequestParam Long userId) {
        if (Main.user == null  || !Main.user.isAdmin()) {
            return "error";
        } else {
            model.addAttribute("user", mainService.getUser(userId));
            return "userPage";
        }
    }

    @GetMapping("/userProperty")
    public String userProperty(ModelMap model,@RequestParam Long userId) {
        if (Main.user == null) {
            return "error";
        } else {
            User user = mainService.getUser(userId);
            model.addAttribute("user",user);
            model.addAttribute("properties",user.getPropertyes());
            return "userProperty";
        }
    }

    @GetMapping("/userIncomes")
    public String userIncomes(ModelMap model,@RequestParam Long userId) {
        if (Main.user == null) {
            return "error";
        } else {
            User user = mainService.getUser(userId);
            model.addAttribute("user",user);
            model.addAttribute("income",user.getIncomes());
            return "userIncomes";
        }
    }

    @GetMapping("/userTransport")
    public String userTransport(ModelMap model,@RequestParam Long userId) {
        if (Main.user == null) {
            return "error";
        } else {
            User user = mainService.getUser(userId);
            model.addAttribute("user",user);
            model.addAttribute("transport",user.getTransports());
            return "userTransport";
        }
    }

    @GetMapping("/userInvoices")
    public String userIncoices(ModelMap model, @RequestParam Long userId) {
        if (Main.user == null) {
            return "error";
        } else {
            User user = mainService.getUser(userId);
            model.addAttribute("user",user);
            model.addAttribute("invoices", InvoiceDTO.getInvoices(user));
            return "userInvoices";
        }
    }

    @GetMapping("/search")
    public String search(ModelMap model, @RequestParam String name) {
        if (Main.user == null || !Main.user.isAdmin()) {
            return "error";
        } else {
            model.addAttribute("users", mainService.search(name));
            return "userList";
        }
    }

   @GetMapping("/payInvoice/transport")
    public String payInvoiceTr(ModelMap model,@RequestParam Long invoiceId) {
       InvoiceTransport invoice = mainService.getInvoiceTransport(invoiceId);
       invoice.setPaid(true);
       mainService.saveInvoceTransport(invoice);
       return "ok";
   }

    @GetMapping("/payInvoice/property")
    public String payInvoicePr(ModelMap model,@RequestParam Long invoiceId) {
        InvoiceProperty invoice = mainService.getInvoiceProperty(invoiceId);
        invoice.setPaid(true);
        mainService.saveInvoiceProperty(invoice);
        return "ok";
    }

    @GetMapping("/payInvoice/income")
    public String payInvoiceIn(ModelMap model,@RequestParam Long invoiceId) {
        InvoiceIncome invoice = mainService.getInvoiceIncome(invoiceId);
        invoice.setPaid(true);
        mainService.saveInvoceIncome(invoice);
        return "ok";
    }

    @GetMapping("addInvoice/income")
    public String addInvoiceIn(ModelMap model, @RequestParam Long incomeId) {
        Income income = mainService.getIncome(incomeId);
        InvoiceIncome invoiceIncome = new InvoiceIncome();
        if (income.getUser().isResident()) {
            invoiceIncome.setPrice(income.getSize()*0.18);
        } else {
            invoiceIncome.setPrice(income.getSize()*0.3);
        }
        invoiceIncome.setDate(new Date());
        invoiceIncome.setPaid(false);
        invoiceIncome.setIncome(income);
        mainService.saveInvoceIncome(invoiceIncome);
        return "ok";
    }

    @GetMapping("addInvoice/property")
    public String addInvoicePr(ModelMap model, @RequestParam Long propertyId) {
        Property property = mainService.getProperty(propertyId);
        InvoiceProperty invoiceProperty = new InvoiceProperty();
        if (property.getPrice() < 300000000) {
            invoiceProperty.setPrice(property.getPrice()*0.001);
        } else {
            invoiceProperty.setPrice(property.getPrice()*0.02);
        }
        invoiceProperty.setDate(new Date());
        invoiceProperty.setPaid(false);
        invoiceProperty.setProperty(property);
        mainService.saveInvoiceProperty(invoiceProperty);
        return "ok";
    }

    @GetMapping("addInvoice/transport")
    public String addInvoice(ModelMap model, @RequestParam Long transportId) {
        Transport transport = mainService.getTransport(transportId);
        InvoiceTransport invoiceTransport = new InvoiceTransport();
        if (transport.getHorsepower() < 150) {
            invoiceTransport.setPrice(transport.getHorsepower()*30);
        } else {
            invoiceTransport.setPrice(transport.getHorsepower()*50);
        }
        invoiceTransport.setDate(new Date());
        invoiceTransport.setPaid(false);
        invoiceTransport.setTransport(transport);
        mainService.saveInvoceTransport(invoiceTransport);
        return "ok";
    }

}
