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
 */

class Resumo (private val transacoes: List<Transacao>){

    fun receita() : BigDecimal{
        var totalReceita = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceita = totalReceita.plus(transacao.valor)
            }
        }
        return totalReceita
    }

    fun despesa() : BigDecimal{
        var totalDespesa = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalDespesa = totalDespesa.plus(transacao.valor)
            }
        }
        return totalDespesa
    }

    fun total() : BigDecimal{
        return receita().subtract(despesa())
    }

}