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

  public boolean atualizarTituloTarefa(String id, String novoTitulo) {
    Tarefa tarefa = getTarefa(id);
    if (tarefa != null && novoTitulo != null && !novoTitulo.isEmpty()) {
      tarefa.setTitulo(novoTitulo);
      return true;
    }
    return false;
  }

  public boolean atualizarDataDeVencimentoTarefa(String id, Date novaDataDeVencimento) {
    Tarefa tarefa = getTarefa(id);
    if (tarefa != null && novaDataDeVencimento != null) {
      tarefa.setDataDeVencimento(novaDataDeVencimento);
      return true;
    }
    return false;
  }

  public boolean atualizarPrioridadeTarefa(String id, Prioridade prioridade) {
    Tarefa tarefa = getTarefa(id);
    if (tarefa != null && prioridade != null) {
      tarefa.setPrioridade(prioridade);
      return true;
    }
    return false;
  }
  
  public List<Tarefa> listaDeTarefas() {
    return tarefasRepository.listaDeTarefas();
  }

  private boolean isValidDate(Date date) {
    Date dataAtual = new Date();
    return !date.before(dataAtual);
  }

  private Tarefa getTarefa(String id) {
    return tarefasRepository.findById(id);
  }
}
