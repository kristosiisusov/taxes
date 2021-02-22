package app.dto;

import app.entityes.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceDTO {
    Integer invoiceId;
    private Date date;
    private double price;
    private String type;
    private boolean isPaid;


    public static List<InvoiceDTO> getInvoices(User user) {
        List<InvoiceIncome> invoiceIncomes = new ArrayList<>();
        for (Income income:
             user.getIncomes()) {
            invoiceIncomes.addAll(income.getInvoices());
        }
        List<InvoiceProperty> invoiceProperties = new ArrayList<>();
        for (Property p:
             user.getPropertyes()) {
            invoiceProperties.addAll(p.getInvoices());
        }
        List<InvoiceTransport> invoiceTransports = new ArrayList<>();
        for (Transport t:
             user.getTransports()) {
            invoiceTransports.addAll(t.getInvoices());
        }
        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        invoiceDTOS.addAll(invoiceIncomes.stream().map(invoice-> getDTO(invoice)).collect(Collectors.toList()));
        invoiceDTOS.addAll(invoiceProperties.stream().map(invoiceIncome -> getDTO(invoiceIncome)).collect(Collectors.toList()));
        invoiceDTOS.addAll(invoiceTransports.stream().map(invoiceIncome -> getDTO(invoiceIncome)).collect(Collectors.toList()));
        invoiceDTOS.sort(new Comparator<InvoiceDTO>() {
            @Override
            public int compare(InvoiceDTO o1, InvoiceDTO o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return invoiceDTOS;
    }

    private static InvoiceDTO getDTO(InvoiceIncome invoiceIncome) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.type = "Подоходный налог";
        invoiceDTO.date = invoiceIncome.getDate();
        invoiceDTO.invoiceId = invoiceIncome.getIncoiceId();
        invoiceDTO.isPaid = invoiceIncome.isPaid();
        invoiceDTO.price = invoiceIncome.getPrice();
        return invoiceDTO;
    }

    private static InvoiceDTO getDTO(InvoiceTransport invoiceIncome) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.type = "Транспортный налог";
        invoiceDTO.date = invoiceIncome.getDate();
        invoiceDTO.invoiceId = invoiceIncome.getIncoiceId();
        invoiceDTO.isPaid = invoiceIncome.isPaid();
        invoiceDTO.price = invoiceIncome.getPrice();
        return invoiceDTO;
    }

    private static InvoiceDTO getDTO(InvoiceProperty invoiceIncome) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.type = "Налог на недвижимость";
        invoiceDTO.date = invoiceIncome.getDate();
        invoiceDTO.invoiceId = invoiceIncome.getIncoiceId();
        invoiceDTO.isPaid = invoiceIncome.isPaid();
        invoiceDTO.price = invoiceIncome.getPrice();
        return invoiceDTO;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
