package dao;

import entities.Categorie;

import java.util.List;

public interface CategorieDao {
    public List<Categorie> listCategorie();
    public int getIdCategorie (String nom);
}
