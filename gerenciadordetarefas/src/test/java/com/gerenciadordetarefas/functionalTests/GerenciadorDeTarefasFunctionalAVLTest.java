package com.gerenciadordetarefas.functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.gerenciadordetarefas.enums.Prioridade;
import com.gerenciadordetarefas.model.Tarefa;
import com.gerenciadordetarefas.repositories.TarefasRepository;
import com.gerenciadordetarefas.service.GerenciadorDeTarefasServiceImpl;

public class GerenciadorDeTarefasFunctionalAVLTest {

  private GerenciadorDeTarefasServiceImpl gerenciador;

  @Before
  public void setup() {
    List<Tarefa> tarefas = new ArrayList<>();
    TarefasRepository tarefasRepository = new TarefasRepository(tarefas);
    this.gerenciador = new GerenciadorDeTarefasServiceImpl(tarefasRepository);
  }

  private void assertTarefaDetalhesIguais(String mensagem, Tarefa tarefa, String titulo, String descricao, Date dataVencimento, Prioridade prioridade) {
    assertEquals(mensagem, titulo, tarefa.getTitulo());
    assertEquals(mensagem, descricao, tarefa.getDescricao());
    assertEquals(mensagem, dataVencimento, tarefa.getDataDeVencimento());
    assertEquals(mensagem, prioridade, tarefa.getPrioridade());
  }

  // Casos de Teste
  // Técnica: Análise de Valores Limites 

  // [CT01] Todas variáveis - Mínimo
  @Test
  public void testCT01TodasVariaveisMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 999); // Data de hoje
    Tarefa tarefa = gerenciador.criarTarefa("T", "d", dataDeVencimento, Prioridade.BAIXA);

    System.out.println(tarefa);
    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "T", "d", dataDeVencimento, Prioridade.BAIXA);
  }

  // [CT02] Variável Título - Mínimo
  @Test
  public void testCT02VariavelTituloMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("T", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "T", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);
  }

  // [CT03] Variável Descrição - Mínimo
  @Test
  public void testCT03VariavelDescricaoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "d", dataDeVencimento, Prioridade.MEDIA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "Título qualquer", "d", dataDeVencimento, Prioridade.MEDIA);
  }

  // [CT04] Variável Data de Vencimento - Mínimo
  @Test
  public void testCT04VariavelDataVencimentoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 999); // Data de hoje
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);
  }

  // [CT05] Variável Prioridade - Mínimo
  @Test
  public void testCT05VariavelPrioridadeMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 999); // Data de hoje
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);
  }

  // [CT06] Todas variáveis - Logo acima do mínimo
  @Test
  public void testCT06TodasVariaveisLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Tí", "de", dataDeVencimento, Prioridade.MEDIA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "Tí", "de", dataDeVencimento, Prioridade.MEDIA);
  }

  // [CT07] Variável Título - Logo acima do mínimo
  @Test
  public void testCT07VariavelTituloLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Tí", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "Tí", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);
  }

  // [CT08] Variável Descrição - Logo acima do mínimo
  @Test
  public void testCT08VariavelDescricaoLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "de", dataDeVencimento, Prioridade.BAIXA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "Título qualquer", "de", dataDeVencimento, Prioridade.BAIXA);
  }

  // [CT09] Variável Data de Vencimento - Logo acima do mínimo
  @Test
  public void testCT09VariavelDataVencimentoLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.BAIXA);
  }

  // [CT10] Variável Prioridade - Logo acima do mínimo
  @Test
  public void testCT10VariavelPrioridadeLogoAcimaDoMinimo() throws ParseException {
    Date dataDeVencimento = new Date(System.currentTimeMillis() + 86400000); // Data de amanhã
    Tarefa tarefa = gerenciador.criarTarefa("Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);

    assertNotNull("A tarefa não deveria ser 'null'", tarefa);
    assertTrue("A tarefa não está presente na lista", gerenciador.listaDeTarefas().contains(tarefa));
    assertTarefaDetalhesIguais("Título incorreto", tarefa, "Título qualquer", "descrição qualquer", dataDeVencimento, Prioridade.MEDIA);
  }

}