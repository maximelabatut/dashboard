package fr.main.data.dao.impl;

import fr.main.data.bo.ProduitBO;
import fr.main.data.dao.ProduitDAO;
import fr.main.data.repository.ProduitRepository;
import fr.main.data.vo.ProduitVO;
import fr.main.data.vo.criteres.ProduitCriteres;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProduitDAOImplTest {

    @Autowired
    ProduitDAO dao;

    @Autowired
    ProduitRepository repository;

    private ProduitBO createProduit(Long index){
        ProduitBO produit = new ProduitBO();
        produit.setId(index);
        produit.setLibelle("Libelle "+index);
        produit.setReference("Reference "+index);
        return produit;
    }

    @Before
    public void before(){
        for(long i = 1 ; i <= 5 ; i++) {
            repository.save(createProduit(i));
        }
    }

    @Test
    public void findByCriteresNullTest(){
        List<ProduitVO> produitsVO = dao.findByCriteres(null);
        Assert.assertEquals(5,produitsVO.size());
        for(int i = 1 ; i <= produitsVO.size() ; i++) {
            Assert.assertEquals(Long.valueOf(i), produitsVO.get(i-1).getId());
            Assert.assertEquals("Libelle "+i, produitsVO.get(i-1).getLibelle());
            Assert.assertEquals("Reference "+i, produitsVO.get(i-1).getReference());
        }
    }

    @Test
    public void findByCriteresTest(){
        ProduitCriteres criteres = new ProduitCriteres();
        criteres.setFiltre("1");
        criteres.setNombreResultatsMax(10);
        List<ProduitVO> produitsVO = dao.findByCriteres(criteres);
        Assert.assertEquals(1,produitsVO.size());
        Assert.assertEquals(Long.valueOf(1), produitsVO.get(0).getId());
        Assert.assertEquals("Libelle 1", produitsVO.get(0).getLibelle());
        Assert.assertEquals("Reference 1", produitsVO.get(0).getReference());
    }

    @Test
    public void findByCriteresCountTest(){
        ProduitCriteres criteres = new ProduitCriteres();
        criteres.setFiltre("1");
        criteres.setNombreResultatsMax(10);
        Long count = dao.findByCriteresCount(criteres);
        Assert.assertNotNull(count);
        Assert.assertEquals(Long.valueOf(1), count);
    }
}
