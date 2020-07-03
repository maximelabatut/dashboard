package fr.main.data.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produit")
@Getter @Setter @NoArgsConstructor
public class ProduitBO implements Serializable {

    private static final long serialVersionUID = 4089123623250354128L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "reference")
    private String reference;
}
