package ru.senla.dto;

import io.swagger.annotations.ApiModelProperty;

public class AdTypeDto {
    @ApiModelProperty(notes = "id of Ad type", name = "id", example = "10")
    private Long id;
    @ApiModelProperty(notes = "category of Ad", name = "category", example = "auto", required = true)
    private String category;
    @ApiModelProperty(notes = "classification of Ad", name = "classification", example = "Volkswagen", required = true)
    private String classification;
    @ApiModelProperty(notes = "type of ad: buy(= true) or sale(= false)", name = "buyOrSale", example = "false",
            value = "true", required = true)
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
