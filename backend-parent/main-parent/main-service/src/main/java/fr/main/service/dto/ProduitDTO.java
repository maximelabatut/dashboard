package fr.main.service.dto;

public class ProduitDTO {

    private Long id;
    private String libelle;
    private String reference;

    public ProduitDTO() {
    }

    public ProduitDTO(Long id, String libelle, String reference) {
        this.id = id;
        this.libelle = libelle;
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}
