package com.example.financask.ui.ui.model
import java.math.BigDecimal

/**
 * Classe resumo recebe uma lista de transações como parâmetro no
 * construtor primário.
 *
 * fun receita -> devolve o total de receitas da lista de transações
 * fun despesa -> devolve o total de despesas da lista de transações
 * fun total -> devolve o total final, ou seja, o total de receitas menos
 * o total de despesas
 *
 * Lâmbda ou Função Anônima
 * Já temos um objeto subentendido dentro dela. Todas as listas de transações dentro dela
 * Eu posso usar o it.
 *
 *Single-Expression Function
 *     fun total() : BigDecimal{
 *return receita().subtract(despesa())
 *} - É o mesmo que eu dizer     fun total() : BigDecimal = receita().subtract(despesa())
 Ou     fun total() = receita().subtract(despesa())
 *
 */

class Resumo (private val transacoes: List<Transacao>){

    val receita get() = somaPor(Tipo.RECEITA)
    val despesa get() = somaPor(Tipo.DESPESA)
    val total get() = receita.subtract(despesa)

    private fun somaPor(tipo: Tipo): BigDecimal{
        val somaDeTransacoesPeloTipo = transacoes
                .filter{ it.tipo == tipo }
                .sumByDouble{ it.valor.toDouble()}
        return  BigDecimal(somaDeTransacoesPeloTipo)
    }
}