package fr.main.service.impl;

import fr.main.data.bo.ProduitBO;
import fr.main.data.dao.ProduitDAO;
import fr.main.data.repository.ProduitRepository;
import fr.main.service.ProduitService;
import fr.main.service.dto.ProduitDTO;
import fr.main.service.dto.mapping.ProduitMap;
import fr.main.service.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private static Logger logger = LoggerFactory.getLogger(ProduitServiceImpl.class);

    @Autowired
    private ProduitDAO dao;

    @Autowired
    private ProduitRepository repository;

    @Transactional(readOnly = true)
    public List<ProduitDTO> findAll(){
        logger.info("findAll");
        return ProduitMap.INSTANCE.boToDtoList(
                repository.findAll()
        );
    }

    @Transactional(readOnly = true)
    public ProduitDTO findById(Long id){
        logger.info("findById");
        Optional<ProduitBO> p = repository.findById(id);
        if(p.isPresent()){
            return ProduitMap.INSTANCE.boToDto(p.get());
        }else {
            throw new ResourceNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public List<ProduitDTO> findByIdIn(List<Long> ids){
        logger.info("findByIdIn");
        return ProduitMap.INSTANCE.boToDtoList(
                repository.findByIdIn(ids)
        );
    }

    public ProduitDTO save(ProduitDTO produitDTO){
        logger.info("save");
        return ProduitMap.INSTANCE.boToDto(
                repository.save(ProduitMap.INSTANCE.dtoToBo(produitDTO))
        );
    }

    public void deleteById(Long id){
        logger.info("deleteById");
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
        }else {
            throw new ResourceNotFoundException();
        }
    }

}
