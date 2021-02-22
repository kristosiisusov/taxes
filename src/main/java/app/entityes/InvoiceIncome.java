package app.entityes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Invoices_Income")
public class InvoiceIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer incoiceId;
    private Date date;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "incomeId")
    private Income income;
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

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
