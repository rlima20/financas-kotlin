package com.example.financask.ui.ui.view
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.financask.R
import com.example.financask.ui.ui.adapter.ListaTransacoesAdapter
import com.example.financask.ui.ui.delegate.TransacaoDelegate
import com.example.financask.ui.ui.dialog.AdicionaTransacaoDialog
import com.example.financask.ui.ui.model.Tipo
import com.example.financask.ui.ui.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    /**
     * MutableListOf - Permite modificar os seus valores internos. Assim conseguimos utilizar collections
     *  que são mutáveis.
     *
     *  Try Expression - Permite que o valor seja devolvido baseando-se no teste que ele fez
     *
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
     *
     * Object-expression
     *
     * AlertDialog - Eu o apresento mas eu preciso construí-lo primeiro.
     * para constriur eu preciso de um builder e criar uma instancia dele
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()//correção
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener() {
            chamaDialogDeAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogDeAdicao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
                .chama(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }
                })
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view: View = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun alteracaoRaphael(): Boolean{
        val a = 1 + 2 + 3
        return false
    }


}