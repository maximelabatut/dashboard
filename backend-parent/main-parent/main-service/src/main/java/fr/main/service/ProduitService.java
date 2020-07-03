package fr.main.service;

import fr.main.service.dto.ProduitDTO;

import java.util.List;

public interface ProduitService {

    List<ProduitDTO> findAll();

    ProduitDTO save(ProduitDTO produit);

    void deleteById(Long id);

    ProduitDTO findById(Long id);

    List<ProduitDTO> findByIdIn(List<Long> id);

}
