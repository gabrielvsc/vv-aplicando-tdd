package com.gerenciadordetarefas.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import com.gerenciadordetarefas.enums.Prioridade;
import com.gerenciadordetarefas.model.Tarefa;
import com.gerenciadordetarefas.repositories.TarefasRepository;

/**
 * Implementação do serviço de gerenciamento de tarefas.
 */
public class GerenciadorDeTarefasServiceImpl implements GerenciadorDeTarefasService {

  private TarefasRepository tarefasRepository;

  /**
   * Construtor da classe.
   *
   * @param tarefasRepository Repositório de tarefas a ser utilizado.
   */
  public GerenciadorDeTarefasServiceImpl(TarefasRepository tarefasRepository) {
    this.tarefasRepository = tarefasRepository;
  }

  /**
   * Cria uma nova tarefa e a adiciona ao repositório de tarefas.
   *
   * @param titulo O título da nova tarefa.
   * @param descricao A descrição da nova tarefa.
   * @param dataDeVencimento A data de vencimento da nova tarefa.
   * @param prioridade A prioridade da nova tarefa.
   * @return A nova tarefa criada, ou null se a tarefa não for válida.
   */
  public Tarefa criarTarefa(String titulo, String descricao, Date dataDeVencimento, Prioridade prioridade) {
    if (!isValidTarefa(new Tarefa(titulo, descricao, dataDeVencimento, prioridade))) {
      return null;
    }

    Tarefa novaTarefa = new Tarefa(titulo, descricao, dataDeVencimento, prioridade);
    tarefasRepository.criarTarefa(novaTarefa);
    return novaTarefa;
  }

  /**
   * Atualiza o título de uma tarefa.
   *
   * @param id O ID da tarefa a ser atualizada.
   * @param novoTitulo O novo título para a tarefa.
   * @return true se o título foi atualizado com sucesso, false caso contrário.
   */
  public boolean atualizarTituloTarefa(String id, String novoTitulo) {
    Tarefa tarefa = getTarefa(id);
    if (!isNullOrEmpty(novoTitulo)) {
      tarefa.setTitulo(novoTitulo);
      return true;
    }
    return false;
  }

  /**
   * Atualiza a data de vencimento de uma tarefa.
   *
   * @param id O ID da tarefa a ser atualizada.
   * @param novaDataDeVencimento A nova data de vencimento para a tarefa.
   * @return true se a data de vencimento foi atualizada com sucesso, false caso contrário.
   */
  public boolean atualizarDataDeVencimentoTarefa(String id, Date novaDataDeVencimento) {
    Tarefa tarefa = getTarefa(id);
    if (!isNullOrEmpty(novaDataDeVencimento.toString()) && isValidDate(novaDataDeVencimento)) {
      tarefa.setDataDeVencimento(novaDataDeVencimento);
      return true;
    }
    return false;
  }

  /**
   * Atualiza a prioridade de uma tarefa.
   *
   * @param id O ID da tarefa a ser atualizada.
   * @param prioridade A nova prioridade para a tarefa.
   * @return true se a prioridade foi atualizada com sucesso, false caso contrário.
   */
  public boolean atualizarPrioridadeTarefa(String id, Prioridade prioridade) {
    Tarefa tarefa = getTarefa(id);
    if (!isNullOrEmpty(prioridade.toString())) {
      tarefa.setPrioridade(prioridade);
      return true;
    }
    return false;
  }

  /**
   * Atualiza a descrição de uma tarefa.
   *
   * @param id O ID da tarefa a ser atualizada.
   * @param novaDescricao A nova descrição para a tarefa.
   * @return true se a descrição foi atualizada com sucesso, false caso contrário.
   */
  public boolean atualizarDescricaoTarefa(String id, String novaDescricao) {
    Tarefa tarefa = getTarefa(id);
    if (!isNullOrEmpty(novaDescricao)) {
      tarefa.setDescricao(novaDescricao);
      return true;
    }
    return false;
  }

  /**
   * Exclui uma tarefa.
   *
   * @param id O ID da tarefa a ser excluída.
   * @return true se a tarefa foi excluída com sucesso, false caso contrário.
   */
  public boolean excluirTarefa(String id) {
    Tarefa tarefa = getTarefa(id);
    if (tarefa != null) {
      tarefasRepository.excluirTarefa(id);
      return true;
    }
    return false;
  }

  /**
   * Obtém a lista de tarefas ordenada por data de vencimento e prioridade.
   *
   * @return Lista de tarefas ordenada.
   */
  public List<Tarefa> listaDeTarefas() {
    List<Tarefa> tarefas = tarefasRepository.listaDeTarefas();
    Collections.sort(tarefas, new Comparator<Tarefa>() {
      public int compare(Tarefa tarefa1, Tarefa tarefa2) {
        int comparison = tarefa1.getDataDeVencimento().compareTo(tarefa2.getDataDeVencimento());
        if (comparison == 0) {
          return tarefa1.getPrioridade().compareTo(tarefa2.getPrioridade());
        }
        return comparison;
      }
    });
    return tarefas;
  }

  /**
   * Verifica se uma data é válida, ou seja, não está antes da data atual.
   *
   * @param date A data a ser verificada.
   * @return true se a data for válida, false caso contrário.
   */
  public boolean isValidDate(Date date) {
    Date dataAtual = new Date();
    return !date.before(dataAtual);
  }

  /**
   * Obtém uma tarefa pelo seu ID.
   *
   * @param id O ID da tarefa.
   * @return A tarefa correspondente ao ID.
   * @throws NoSuchElementException Se a tarefa não for encontrada.
   */
  public Tarefa getTarefa(String id) throws NoSuchElementException {
    Tarefa tarefa = tarefasRepository.findById(id);
    if (tarefa == null) {
      throw new NoSuchElementException("Tarefa não encontrada");
    }
    return tarefa;
  }

  /**
   * Verifica se uma string é nula ou vazia.
   *
   * @param value A string a ser verificada.
   * @return true se a string for nula ou vazia, false caso contrário.
   */
  private boolean isNullOrEmpty(String value) {
    return value == null || value.isEmpty();
  }

  /**
   * Verifica se uma tarefa é válida.
   *
   * @param tarefa A tarefa a ser verificada.
   * @return true se a tarefa for válida, false caso contrário.
   */
  private boolean isValidTarefa(Tarefa tarefa) {
    return tarefa != null && 
      !isNullOrEmpty(tarefa.getTitulo()) &&
      !isNullOrEmpty(tarefa.getDescricao()) &&
      isValidDate(tarefa.getDataDeVencimento()) &&
      tarefa.getPrioridade() != null;
  }
}
