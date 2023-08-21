package com.gerenciadordetarefas.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import com.gerenciadordetarefas.enums.Prioridade;
import com.gerenciadordetarefas.model.Tarefa;

public interface GerenciadorDeTarefasService {

  public Tarefa criarTarefa(String titulo, String descricao, Date dataDeVencimento, Prioridade prioridade);

  public boolean atualizarTituloTarefa(String id, String novoTitulo);

  public boolean atualizarDataDeVencimentoTarefa(String id, Date novaDataDeVencimento);

  public boolean atualizarPrioridadeTarefa(String id, Prioridade prioridade);

  public boolean atualizarDescricaoTarefa(String id, String novaDescricao);

  public boolean excluirTarefa(String id);

  public List<Tarefa> listaDeTarefas();

  public boolean isValidDate(Date date);

  public Tarefa getTarefa(String id) throws NoSuchElementException;
}
