package kz.pavershin.models.abstracts;

import kz.pavershin.models.Product;

import javax.persistence.*;

@MappedSuperclass
public abstract class ItemsProduct {

    @OneToOne
    @JoinColumn
    private Product product;

    private Integer quantity;

    private Integer margin;

    @Column(name = "total_margin")
    private Integer totalMargin;

    private Integer sellingPrice;

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getMargin() { return margin; }
    public void setMargin(Integer margin) { this.margin = margin; }

    public Integer getTotalMargin() { return totalMargin; }
    public void setTotalMargin(Integer totalMargin) { this.totalMargin = totalMargin; }

    public Integer getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(Integer sellingPrice) { this.sellingPrice = sellingPrice; }
}
