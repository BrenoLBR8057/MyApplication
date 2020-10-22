package DAO;

import java.util.ArrayList;
import java.util.List;

import model.Model;

public class PIDAO {
    private static int lastId = 0;
    private static List<Model> itensList = new ArrayList<>();

    public void insert(Model itens){
        itens.setId(lastId);
        itensList.add(itens);
        lastId++;
    }

    public void remove(int id){itensList.remove(id);}

    public void edit(int id, Model itens){itensList.set(id, itens);}

    public void edit(Model itens){
        int id = itens.getId();
        for (Model item: itensList) {
            if (item.getId()== id){
                itensList.set(id, itens);
            }
        }
    }

    public List<Model> recoverAllItensList(){return itensList;}
}
