package fr.main.service.dto.mapping;

import fr.main.data.bo.ProduitBO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import fr.main.service.dto.ProduitDTO;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProduitMap {

    ProduitMap INSTANCE = Mappers.getMapper(ProduitMap.class);

    List<ProduitBO> dtoToBoList(List<ProduitDTO> produitDTO);

    ProduitBO dtoToBo(ProduitDTO produitDTO);

    List<ProduitDTO> boToDtoList(List<ProduitBO> produit);

    ProduitDTO boToDto(ProduitBO produit);

}
