package fr.main.webservice.controller;

import fr.main.service.ProduitService;
import fr.main.service.dto.ProduitDTO;
import fr.main.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/produit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProduitController {

    @Autowired
    private ProduitService service;

    /**
     * Creation de produit
     *
     * @param produit produit a creer
     * @return produit cree
     */
    @PostMapping(value = "/")
    public ResponseEntity<ProduitDTO> save(@Valid @RequestBody ProduitDTO produit) {

        ProduitDTO created = this.service.save(produit);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(produit.getId()).toUri();

        return ResponseEntity.created(location).body(created);
    }

    /**
     * Lecture de produit par identifiant
     * @param id id du produit
     * @return produit
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProduitDTO> findById(@PathVariable("id") Long id) {

        try{
            ProduitDTO produit = this.service.findById(id);
            return ResponseEntity.ok(produit);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Lecture de produits
     *
     * @return liste de produits
     */
    @GetMapping(value = "/")
    public ResponseEntity<List<ProduitDTO>> findAll() {

        return ResponseEntity.ok(this.service.findAll());
    }

    /**
     * Modification de produit
     *
     * @param produit a modifier
     * @return produit modifie
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProduitDTO> update(@RequestBody ProduitDTO produit) {

        return ResponseEntity.ok().body(this.service.save(produit));
    }

    /**
     * Suppression de produit
     *
     * @param id du produit a supprimer
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {

        try {
            this.service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
