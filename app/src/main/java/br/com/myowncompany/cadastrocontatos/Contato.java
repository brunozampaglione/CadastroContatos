package br.com.myowncompany.cadastrocontatos;

import java.io.Serializable;

/**
 * Created by android5193 on 08/04/15.
 */
public class Contato implements Serializable {

    private Long Id;
    private String Nome;
    private String Endereco;
    private String Telefone;
    private String Site;
    private Double Nota;
    private String Foto;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public Double getNota() {
        return Nota;
    }

    public void setNota(Double nota) {
        Nota = nota;
    }

    @Override
    public String toString(){
        return Id + " - " + Nome;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }
}
