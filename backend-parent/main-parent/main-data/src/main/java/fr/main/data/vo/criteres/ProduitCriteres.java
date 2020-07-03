package fr.main.data.vo.criteres;

import io.ebean.LikeType;

import java.io.Serializable;

public class ProduitCriteres implements Serializable {

    private String filtre;

    private LikeType likeTypeFiltre;

    private Integer nombreResultatsMax;

    public String getFiltre() {
        return filtre;
    }

    public void setFiltre(String filtre) {
        this.filtre = filtre;
    }

    public LikeType getLikeTypeFiltre() {
        return likeTypeFiltre;
    }

    public void setLikeTypeFiltre(LikeType likeTypeFiltre) {
        this.likeTypeFiltre = likeTypeFiltre;
    }

    public Integer getNombreResultatsMax() {
        return nombreResultatsMax;
    }

    public void setNombreResultatsMax(Integer nombreResultatsMax) {
        this.nombreResultatsMax = nombreResultatsMax;
    }
}
