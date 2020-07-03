package fr.main.service.impl;

import fr.main.service.ProduitService;
import fr.main.service.dto.ProduitDTO;
import fr.main.service.exception.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProduitServiceImplTest extends Mockito {

    @Autowired
    private ProduitService service;

    private ProduitDTO createProduit(Long index){
        ProduitDTO produit = new ProduitDTO();
        produit.setId(index);
        produit.setLibelle("Libelle "+index);
        produit.setReference("Reference "+index);
        return produit;
    }

    @Before
    public void before(){
        for(long i = 1 ; i <= 5 ; i++) {
            service.save(createProduit(i));
        }
    }

    @Test
    public void findAllTest() {
        List<ProduitDTO> produits = service.findAll();
        Assert.assertNotNull(produits);
        Assert.assertEquals(5, produits.size());
        for(int i = 1 ; i <= produits.size() ; i++) {
            Assert.assertEquals(Long.valueOf(i), produits.get(i-1).getId());
            Assert.assertEquals("Libelle "+i, produits.get(i-1).getLibelle());
            Assert.assertEquals("Reference "+i, produits.get(i-1).getReference());
        }
    }

    @Test
    public void findByIdOK() {
        ProduitDTO produit = service.findById(1L);
        Assert.assertNotNull(produit);
        Assert.assertEquals(Long.valueOf(1L), produit.getId());
        Assert.assertEquals("Libelle " + 1L, produit.getLibelle());
        Assert.assertEquals("Reference " + 1L, produit.getReference());
    }

    /*
    @Test
    public void findByIdInTest() {
        List<ProduitDTO> produits = service.findByIdIn(Arrays.asList(1L, 2L));
        Assert.assertNotNull(produits);
        Assert.assertEquals(2, produits.size());
        for(int i = 1 ; i <= produits.size() ; i++) {
            Assert.assertEquals(Long.valueOf(i), produits.get(i-1).getId());
            Assert.assertEquals("Libelle "+i, produits.get(i-1).getLibelle());
            Assert.assertEquals("Reference "+i, produits.get(i-1).getReference());
        }
    }
     */

    @Test(expected = ResourceNotFoundException.class)
    public void findByIdKO() {
        service.findById(999L);
    }

    @Test
    public void saveTest() {
        ProduitDTO produit = service.save(createProduit(1L));
        Assert.assertNotNull(produit);
        Assert.assertEquals(Long.valueOf(1L), produit.getId());
        Assert.assertEquals("Libelle " + 1L, produit.getLibelle());
        Assert.assertEquals("Reference " + 1L, produit.getReference());
    }

    @Test
    public void deleteByIdTestOK() {
        service.deleteById(1L);
        List<ProduitDTO> produitsDTO = service.findAll();
        for(ProduitDTO produitDTO : produitsDTO){
            Assert.assertNotEquals(Long.valueOf(1L), produitDTO.getId());
        }
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteByIdTestKO() {
        service.deleteById(999L);
    }
}
