package com.example.financask.ui.ui.view
import android.content.Context
import android.service.controls.templates.ControlTemplate
import android.view.View
import androidx.core.content.ContextCompat
import com.example.financask.R
import com.example.financask.ui.ui.extension.formataParaBrasileiro
import com.example.financask.ui.ui.model.Resumo
import com.example.financask.ui.ui.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

/**
 * Essa classe resumoView recebe 3 parâmetros no construtor primário,
 * um contexto, uma view e uma lista de transações
 *
 * Temos também 3 properties uma do tipo Resumo,
 * corReceita e corDespesa
 *
 * Adiciona Receita -> Variável totalReceita recebe um bigDecimal
 * A feature (with) -> Eu consigo dizer, com o objeto que eu quero, no caso
 * o view.resumo_card_receita, você pode executar tudo que é relacionado a
 * esse objeto aqui dentro desse escopo; Eu posso chamar o setTextColor direto pois ele vai
 * ser de fato desse resumo_card_receita. Com isso eu não preciso chamar o objeto várias vezes.
 *
 * No adicionaDespesa eu faço o mesmo.
 *
 * Já na função adicionaTotal -> Se o total é maior ou igual a zero a cor
 * é configurada para cor de receita. Se não, para a cor de despesa
 *
 */
class ResumoView(private val context: Context,
                 private val view: View,
                 transacoes: List<Transacao>) {

    private val resumo: Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()
        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }
    }

    fun adicionaDespesa() {
        var totalDespesa = resumo.despesa()
        with(view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }

    fun adicionaTotal() {
        //with()
        val total: BigDecimal = resumo.total()
        val cor = corPor(total)
        view.resumo_card_total.text = total.formataParaBrasileiro()
    }

    private fun corPor(valor: BigDecimal) : Int {
        if (valor.compareTo(BigDecimal.ZERO) >= 0) {
            return corReceita
        }
            return corDespesa
    }

    //linha
    //linha
}