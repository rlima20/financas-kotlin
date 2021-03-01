package com.example.financask.ui.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.example.financask.R
import com.example.financask.ui.ui.extension.formataParaBrasileiro
import com.example.financask.ui.ui.extension.limitaEmAte
import com.example.financask.ui.ui.model.Tipo
import com.example.financask.ui.ui.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

//Construtor Primário
//Any - É o object do Java. É a super classe de todas as classes do Kotlin
class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val contexto: Context) : BaseAdapter(){

    private val limiteDaCategoria = 14

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(contexto).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]
        adicionaValor(transacao, viewCriada)
        adicionaIcone(transacao, viewCriada)
        adicionaCategoria(viewCriada, transacao)
        adicionaData(viewCriada, transacao)
        return viewCriada
    }

    private fun adicionaCategoria(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaData(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }

    private fun adicionaIcone(transacao: Transacao, viewCriada: View) {
        val icone = iconePor(transacao.tipo)
        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo)  : Int{
            if (tipo == Tipo.RECEITA) {
                return R.drawable.icone_transacao_item_receita
            }
                return R.drawable.icone_transacao_item_despesa
    }

    private fun adicionaValor(transacao: Transacao, viewCriada: View) {
        val cor: Int = corPor(transacao.tipo)
        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPor(tipo: Tipo) : Int{
        if (tipo == Tipo.RECEITA) {
            return ContextCompat.getColor(contexto, R.color.receita)
        }
            return ContextCompat.getColor(contexto, R.color.despesa)
    }
}