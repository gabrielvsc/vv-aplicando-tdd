package com.gerenciadordetarefas;

class Tarefa {
    private String titulo;
    private String descricao;
    private String dataDeVencimento;
    private Prioridade prioridade;

    public Tarefa(String titulo, String descricao, String dataDeVencimento, Prioridade prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataDeVencimento = dataDeVencimento;
        this.prioridade = prioridade;
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

    public String getDataDeVencimento() {
      return dataDeVencimento;
    }

    public void setDataDeVencimento(String dataDeVencimento) {
      this.dataDeVencimento = dataDeVencimento;
    }

    public Prioridade getPrioridade() {
      return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
      this.prioridade = prioridade;
    }
}