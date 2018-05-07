package kz.pavershin.models;

import kz.pavershin.models.abstracts.ItemsProduct;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RevisionProduct extends ItemsProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn
    private Revision revisionId;

    public Revision getRevisionId() { return revisionId; }
    public void setRevisionId(Revision revisionId) { this.revisionId = revisionId; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SaleProduct)) {
            return false;
        }
        RevisionProduct other = (RevisionProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SaleProduct{" +
                "id=" + id +
                ", saleId=" + revisionId +
                ", PRODUCT=" + getProduct().getName() +
                ", PRODUCT=" + getProduct().getCode() +
                '}';
    }
}
