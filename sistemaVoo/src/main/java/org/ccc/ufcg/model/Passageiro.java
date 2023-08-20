package org.ccc.ufcg.model;

public class Passageiro {

    private String nome;
    private String telefone;
    private String email;
    private Passagem passagem;

    public Passageiro(String nome, String telefone, String email, Passagem passagem) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.passagem = passagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }
}
