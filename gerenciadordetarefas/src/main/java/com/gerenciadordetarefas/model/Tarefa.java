package com.gerenciadordetarefas.model;

import java.util.Date;
import java.util.UUID;

import com.gerenciadordetarefas.enums.Prioridade;

public class Tarefa {

  private String id;
  private String titulo;
  private String descricao;
  private Date dataDeVencimento;
  private Prioridade prioridade;

  public Tarefa(String titulo, String descricao, Date dataDeVencimento, Prioridade prioridade) {
    this.id = UUID.randomUUID().toString();  
    this.titulo = titulo;
    this.descricao = descricao;
    this.dataDeVencimento = dataDeVencimento;
    this.prioridade = prioridade;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
    
  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Date getDataDeVencimento() {
    return dataDeVencimento;
  }

  public void setDataDeVencimento(Date dataDeVencimento) {
    this.dataDeVencimento = dataDeVencimento;
  }

  public Prioridade getPrioridade() {
    return prioridade;
  }

  public void setPrioridade(Prioridade prioridade) {
    this.prioridade = prioridade;
  }
}