package org.ccc.ufcg.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voo {

    private String codigo;
    private LocalDate data;
    private LocalDateTime hora;
    private BigDecimal valor;
    private String origem;
    private String destino;
    private int numeroPassageiros;
    private int numeroAcentosReservados;
    private List<Passageiro> passageiros = new ArrayList<>();
    private Boolean isLotado;


    public Voo() {
    }

    public Voo(String codigo, LocalDate data, LocalDateTime hora, BigDecimal valor, String origem, String destino, int numeroPassageiros) {
        this.codigo = codigo;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.origem = origem;
        this.destino = destino;
        this.numeroPassageiros = numeroPassageiros;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public List<Passageiro> getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(List<Passageiro> passageiros) {
        this.passageiros = passageiros;
    }

    public int getAcentosVazios() {
        return this.numeroPassageiros - this.numeroAcentosReservados;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voo voo = (Voo) o;
        return Objects.equals(codigo, voo.codigo) && Objects.equals(data, voo.data) && Objects.equals(hora, voo.hora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, data, hora);
    }
    @Override
    public String toString() {
        return String.format("Origem: %s, Destino: %s, Horário: %s, Preço: %s, Lugares vazios: %s.\n", origem, destino, hora, valor, getAcentosVazios());
    }
}
