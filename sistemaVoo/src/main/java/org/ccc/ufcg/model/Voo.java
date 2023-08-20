package org.ccc.ufcg.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Voo {
    private LocalDate data;
    private LocalDateTime hora;
    private BigDecimal valor;
    private String origem;
    private String destino;
    private int numeroPassageiros;
    private int numeroAcentosReservados;
    private List<Passagem> passagems = new ArrayList<>();
    private Boolean isLotado;


    public Voo() {
    }

    public Voo(LocalDate data, LocalDateTime hora, BigDecimal valor, String origem, String destino, int numeroPassageiros) {
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.origem = origem;
        this.destino = destino;
        this.numeroPassageiros = numeroPassageiros;
        this.isLotado = false;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getNumeroPassageiros() {
        return numeroPassageiros;
    }

    public void setNumeroPassageiros(int numeroPassageiros) {
        this.numeroPassageiros = numeroPassageiros;
    }

    public int getNumeroAcentosReservados() {
        return numeroAcentosReservados;
    }

    public void setNumeroAcentosReservados(int numeroAcentosReservados) {
        this.numeroAcentosReservados = numeroAcentosReservados;
    }

    public Boolean getLotado() {
        return isLotado;
    }

    public void setLotado(Boolean lotado) {
        isLotado = lotado;
    }

    public List<Passagem> getPassagems() {
        return passagems;
    }

    public void setPassagems(List<Passagem> passagems) {
        this.passagems = passagems;
    }
}
