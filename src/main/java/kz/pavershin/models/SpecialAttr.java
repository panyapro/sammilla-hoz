package kz.pavershin.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SpecialAttr {

    /*
     * Fields
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialKey;

    private String value;


    /*
     * Plain getters/setters
     */

    public Long getId() { return id; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getSpecialKey() { return specialKey; }
    public void setSpecialKey(String specialKey) { this.specialKey = specialKey; }

    /*
         * Entity methods
         */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SpecialAttr)) {
            return false;
        }
        SpecialAttr other = (SpecialAttr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", specialKey='" + specialKey + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}