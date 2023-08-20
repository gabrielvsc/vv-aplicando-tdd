package com.gerenciadordetarefas;

import java.util.Date;

public class GerenciadorDeTarefasService {

    private GerenciadorDeTarefas gerenciador;

    public GerenciadorDeTarefasService() {
        gerenciador = new GerenciadorDeTarefas();
    }

    public Tarefa criarTarefa(String titulo, String descricao, Date dataDeVencimento, Prioridade prioridade) {
        return gerenciador.criarTarefa(titulo, descricao, dataDeVencimento, prioridade);
    }

    public void atualizarTituloTarefa(Tarefa tarefa, String novoTitulo) {
        if (tarefa != null) {
            tarefa.setTitulo(novoTitulo);
        }
    }
}
