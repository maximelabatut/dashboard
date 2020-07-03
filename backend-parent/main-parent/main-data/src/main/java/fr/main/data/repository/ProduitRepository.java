package fr.main.data.repository;

import fr.main.data.bo.ProduitBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<ProduitBO, Long> {

    List<ProduitBO> findByIdIn(List<Long> ids);

}
