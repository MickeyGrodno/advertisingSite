package ru.senla.dto;

public class AdTypeDto {
    private Long id;
    private String category;
    private String classification;
    private boolean buyOrSale;

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

    public boolean isBuyOrSale() {
        return buyOrSale;
    }

    public void setBuyOrSale(boolean buyOrSale) {
        this.buyOrSale = buyOrSale;
    }
}
