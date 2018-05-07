package kz.pavershin.models;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product {

    public Product(){}

    public Product(String name, String code, Integer arrivalCost, Integer sellingPrice, Category category) {
        this.name = name;
        this.code = code;
        this.sellingPrice = sellingPrice;
        this.category = category;
        this.arrivalCost = arrivalCost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private Integer sellingPrice;

    private Integer arrivalCost;

    @OneToOne
    @JoinColumn
    private Category category;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Integer getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(Integer sellingPrice) { this.sellingPrice = sellingPrice; }

    public Integer getArrivalCost() { return arrivalCost; }
    public void setArrivalCost(Integer arrivalCost) { this.arrivalCost = arrivalCost; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", arrivalCost=" + arrivalCost +
                ", category=" + category +
                '}';
    }
}
