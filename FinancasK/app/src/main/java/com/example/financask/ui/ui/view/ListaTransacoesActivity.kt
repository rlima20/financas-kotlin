package com.example.financask.ui.ui.view
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.financask.R
import com.example.financask.ui.ui.adapter.ListaTransacoesAdapter
import com.example.financask.ui.ui.extension.formataParaBrasileiro
import com.example.financask.ui.ui.model.Tipo
import com.example.financask.ui.ui.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.resumo_card.*
import java.math.BigDecimal
import java.util.*
class ListaTransacoesActivity : AppCompatActivity() {

    /**
     * Nessa classe estou declarando uma variável que não irá ser alterada que é uma lista de Transações.
     * Estou também instanciando 4 objetos desse tipo e passando os parâmetros através do recurso chamado Named Parameter.
     * Esse recurso vem com a proposta de permitir uma sobrecarga de construtores e funções de tal forma que não seja
     * necessário manter a mesma ordem das sobrecargas. Para isso, basta apenas chamar a label do parâmetro,
     * adicionar um operador de igual e enviar o valor, por exemplo, para enviar
     * o valor da property valor em uma instância da classe Transacao utilizando
     * o Named Parameter fica da seguinte maneira: Transacao(valor = BigDecimal(100.0))
     *
     * Consigo também utilizar os componentes de tela diretamente sem precisar chamar o findViewById.
     *
     * Para pegar uma view do window
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesDeExemplo()

        configuraResumo(transacoes)
        configuraLista(transacoes)
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view: View = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo() = listOf(
            Transacao(valor = BigDecimal(20.5), categoria = "Almoço de final de semana", tipo = Tipo.DESPESA),
            Transacao(valor = BigDecimal(100.0), categoria = "Economia", tipo = Tipo.RECEITA),
            Transacao(valor = BigDecimal(100.0), tipo = Tipo.DESPESA, data = Calendar.getInstance()),
            Transacao(valor = BigDecimal(479.5), categoria = "Prêmio", tipo = Tipo.DESPESA),
            Transacao(valor = BigDecimal(500), categoria = "Prêmio", tipo = Tipo.RECEITA)
    )
}