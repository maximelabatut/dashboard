package fr.main.data.dao;

import fr.main.data.vo.ProduitVO;
import fr.main.data.vo.criteres.ProduitCriteres;

import java.util.List;

public interface ProduitDAO {

    List<ProduitVO> findByCriteres(ProduitCriteres criteres);

    Long findByCriteresCount(ProduitCriteres criteres);
}
