package ru.senla.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ad_type")

public class AdType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ad_type_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "category", nullable = false, length = 45)
    private String category;

    @Column(name = "classification", nullable = false, length = 45)
    private String classification;

    @Column(name = "buy_or_sale", nullable = false)
    private boolean buyOrSale;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "adType")
    @Cascade(CascadeType.DELETE)
    private List<Ad> adList;

    public AdType() {
    }

    public AdType(String category, String classification, boolean buyOrSale) {
        this.category = category;
        this.classification = classification;
        this.buyOrSale = buyOrSale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public boolean getBuyOrSale() {
        return buyOrSale;
    }

    public void setBuyOrSale(boolean buyOrSale) {
        this.buyOrSale = buyOrSale;
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }
}
