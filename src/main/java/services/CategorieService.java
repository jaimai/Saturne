package services;

import dao.CategorieDao;
import dao.UserDao;
import dao.impl.CategorieDaoImpl;
import dao.impl.UserDaoImpl;
import entities.Categorie;

import javax.swing.event.ListDataEvent;
import java.util.List;

public class CategorieService {
    private static class CategorieServiceHolder {
        private static CategorieService instance = new CategorieService();
    }

    public static CategorieService getInstance() {
        return CategorieService.CategorieServiceHolder.instance;
    }
    private CategorieDao categorieDao= new CategorieDaoImpl();
    private CategorieService() {
    }
    public List<Categorie> lesCategories(){
        return categorieDao.listCategorie();
    }
    public int idCategorie(String nom){
        return categorieDao.getIdCategorie(nom);
    }
}
