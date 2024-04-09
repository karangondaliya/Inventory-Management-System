package com.ims.dto;

public class ProductDTO {

    private int id;
    private String name;
    private String description;
    private PartiesDTO party; // PartiesDTO represents the DTO for Parties

    public ProductDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PartiesDTO getParty() {
        return party;
    }

    public void setParty(PartiesDTO party) {
        this.party = party;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", party=" + party +
                '}';
    }
}