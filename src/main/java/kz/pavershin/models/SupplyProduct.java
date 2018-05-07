package kz.pavershin.models;

import kz.pavershin.models.abstracts.ItemsProduct;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SupplyProduct extends ItemsProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn
    private Supply supplyId;

    public Supply getSupplyId() { return supplyId; }
    public void setSupplyId(Supply supplyId) { this.supplyId = supplyId; }

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
        SupplyProduct other = (SupplyProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SaleProduct{" +
                "id=" + id +
                ", supplyId=" + supplyId +
                ", PRODUCT=" + getProduct().getName() +
                ", PRODUCT=" + getProduct().getCode() +
                '}';
    }
}
