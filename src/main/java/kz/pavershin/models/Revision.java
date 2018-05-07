package kz.pavershin.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Revision implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    @Column(name = "revision_date")
    private Date revisionDate;

    public Integer getId() { return id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Date getRevisionDate() { return revisionDate; }
    public void setRevisionDate(Date revisionDate) { this.revisionDate = revisionDate; }

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
        Revision other = (Revision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
