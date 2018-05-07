package kz.pavershin.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Supply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    @Column(name = "supply_date")
    private Date supplyDate;

    public Integer getId() { return id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Date getSupplyDate() { return supplyDate; }
    public void setSupplyDate(Date supplyDate) { this.supplyDate = supplyDate; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sale)) {
            return false;
        }
        Supply other = (Supply) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
