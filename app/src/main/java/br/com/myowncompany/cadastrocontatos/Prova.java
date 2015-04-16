package br.com.myowncompany.cadastrocontatos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android5193 on 15/04/15.
 */
public class Prova {
private String Data;
    private String Materia;
    private List<String> Topicos = new ArrayList<String>();

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String materia) {
        Materia = materia;
    }

    public List<String> getTopicos() {
        return Topicos;
    }

    public void setTopicos(List<String> topicos) {
        Topicos = topicos;
    }

    @Override
    public String toString(){
        return getMateria();
    }
}
