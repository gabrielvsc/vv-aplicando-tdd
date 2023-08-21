package com.gerenciadordetarefas.model;

import com.gerenciadordetarefas.enums.Prioridade;

import java.util.Date;
import java.util.UUID;

/**
 * Representa uma tarefa a ser gerenciada.
 */
public class Tarefa {

  private String id;
  private String titulo;
  private String descricao;
  private Date dataDeVencimento;
  private Prioridade prioridade;

  /**
   * Construtor para criar uma nova tarefa.
   *
   * @param titulo           O título da tarefa.
   * @param descricao        A descrição da tarefa.
   * @param dataDeVencimento A data de vencimento da tarefa.
   * @param prioridade       A prioridade da tarefa.
   */
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