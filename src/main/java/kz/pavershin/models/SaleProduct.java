package kz.pavershin.models;

import kz.pavershin.models.abstracts.ItemsProduct;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sale_product")
public class SaleProduct extends ItemsProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private Sale saleId;

    public Sale getSaleId() { return saleId; }
    public void setSaleId(Sale saleId) { this.saleId = saleId; }

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
        SaleProduct other = (SaleProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SaleProduct{" +
                "id=" + id +
                ", saleId=" + saleId +
                ", PRODUCT=" + getProduct().getName() +
                ", PRODUCT=" + getProduct().getCode() +
                '}';
    }
}
