package fr.main.data.dao.rowmapper;

import fr.main.data.vo.ProduitVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProduitRowMapper implements RowMapper<ProduitVO> {

    private static final String ATTRIBUT_ID = "id";
    private static final String ATTRIBUT_LIBELLE = "libelle";
    private static final String ATTRIBUT_REFERENCE = "reference";

    @Override
    public ProduitVO mapRow(ResultSet resultSet, int i) throws SQLException {
        ProduitVO entity = new ProduitVO();
        entity.setId(resultSet.getLong(ATTRIBUT_ID));
        entity.setLibelle(resultSet.getString(ATTRIBUT_LIBELLE));
        entity.setReference(resultSet.getString(ATTRIBUT_REFERENCE));
        return entity;
    }

}
