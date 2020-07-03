package fr.main.data.dao.impl;

import fr.main.data.dao.ProduitDAO;
import fr.main.data.dao.rowmapper.ProduitRowMapper;
import fr.main.data.dao.utils.QueryWithParameters;
import fr.main.data.dao.utils.SqlTerms;
import fr.main.data.dao.utils.Utils;
import fr.main.data.vo.ProduitVO;
import fr.main.data.vo.criteres.ProduitCriteres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@Component
@PropertySource("classpath:sql/produit.xml")
public class ProduitDAOImpl implements ProduitDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    private Environment environment;

    @Override
    public List<ProduitVO> findByCriteres(ProduitCriteres criteres) {
        QueryWithParameters queryWithParameters = getQueryWithParameters(criteres, false);
        return jdbcTemplate.query(queryWithParameters.getQuery(), queryWithParameters.getParameters(), new ProduitRowMapper());
    }

    @Override
    public Long findByCriteresCount(ProduitCriteres criteres) {
        QueryWithParameters queryWithParameters = getQueryWithParameters(criteres, true);
        return jdbcTemplate.queryForObject(queryWithParameters.getQuery(), queryWithParameters.getParameters(), Long.class);
    }

    private QueryWithParameters getQueryWithParameters(ProduitCriteres criteres, boolean count) {
        QueryWithParameters queryWithParameters = new QueryWithParameters();
        StringBuilder query = new StringBuilder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String queryProperty = "Produit.findByCriteres";

        if (count) {
            query.append(environment.getProperty(queryProperty + ".select.count"));
        } else {
            query.append(environment.getProperty(queryProperty + ".select"));
        }

        query.append(environment.getProperty(queryProperty + ".from"));

        if(criteres != null){
            queryProperty += ".critere";
            if(criteres.getFiltre() != null){
                query.append(environment.getProperty(queryProperty + ".filtre"));
                parameters.addValue("filtre", Utils.setLikeType(criteres.getFiltre(),criteres.getLikeTypeFiltre()), Types.VARCHAR);
            }

            if(criteres.getNombreResultatsMax() != null){
                query.append(environment.getProperty(queryProperty + ".nombreResultatMax"));
                parameters.addValue("nombreResultatsMax", criteres.getNombreResultatsMax(), Types.INTEGER);
            }
        }

        queryWithParameters.setQuery(query.toString().replaceFirst(SqlTerms.AND,SqlTerms.WHERE));
        queryWithParameters.setParameters(parameters);
        return queryWithParameters;
    }


}
