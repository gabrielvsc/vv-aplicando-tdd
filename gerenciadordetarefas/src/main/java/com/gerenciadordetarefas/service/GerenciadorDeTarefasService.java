package com.gerenciadordetarefas.service;

import java.util.Date;
import java.util.List;

import com.gerenciadordetarefas.enums.Prioridade;
import com.gerenciadordetarefas.model.Tarefa;
import com.gerenciadordetarefas.repositories.TarefasRepository;

public class GerenciadorDeTarefasService {

  private TarefasRepository tarefasRepository;

  public GerenciadorDeTarefasService(TarefasRepository tarefasRepository) {
    this.tarefasRepository = tarefasRepository;
  }

  public Tarefa criarTarefa(String titulo, String descricao, Date dataDeVencimento, Prioridade prioridade) {
    if (titulo.isEmpty() || descricao.isEmpty() || dataDeVencimento == null || prioridade == null || !isValidDate(dataDeVencimento)) {
      return null;
    }

    Tarefa novaTarefa = new Tarefa(titulo, descricao, dataDeVencimento, prioridade);
    
    tarefasRepository.criarTarefa(novaTarefa);
    return novaTarefa;
  }

  public void atualizarTituloTarefa(Tarefa tarefa, String novoTitulo) {
    if (tarefa != null) {
      tarefa.setTitulo(novoTitulo);
    }
  }

  public List<Tarefa> listaDeTarefas() {
    return tarefasRepository.listaDeTarefas();
  }

  private boolean isValidDate(Date date) {
    Date dataAtual = new Date();
    return !date.before(dataAtual);
  }
}
