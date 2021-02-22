package app.entityes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Invoices_Property")
public class InvoiceProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer incoiceId;
    private Date date;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "propertyId")
    private Property property;
    private boolean isPaid;

    public Integer getIncoiceId() {
        return incoiceId;
    }

    public void setIncoiceId(Integer incoiceId) {
        this.incoiceId = incoiceId;
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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
