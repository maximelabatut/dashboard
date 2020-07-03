package fr.main.data.dao.utils;

import io.ebean.LikeType;

public class Utils {

    public Utils() {}

    /**
     * Modifie le filtre en fonction du liketype
     * @param filtre le filtre
     * @param likeType le liketype
     * @return le filtre modifie
     */
    public static String setLikeType(String filtre, LikeType likeType){
        if(likeType == null){
            likeType = LikeType.CONTAINS;
        }
        switch (likeType){
            case STARTS_WITH:
                filtre = filtre + "%";
                break;
            case ENDS_WITH:
                filtre = "%" + filtre;
                break;
            default:
                filtre = "%" + filtre + "%";
        }
        return filtre;
    }

}
