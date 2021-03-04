package com.example.financask.ui.ui.view
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.financask.R
import com.example.financask.ui.ui.adapter.ListaTransacoesAdapter
import com.example.financask.ui.ui.extension.formataParaBrasileiro
import com.example.financask.ui.ui.model.Tipo
import com.example.financask.ui.ui.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
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
     *
     * Object-expression
     *
     * AlertDialog - Eu o apresento mas eu preciso construí-lo primeiro.
     * para constriur eu preciso de um builder e criar uma instancia dele
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesDeExemplo()
        configuraResumo(transacoes)
        configuraLista(transacoes)


        lista_transacoes_adiciona_despesa
                .setOnClickListener {
                    Toast.makeText(this,
                            "Toast1",
                            Toast.LENGTH_SHORT).show() }

        lista_transacoes_adiciona_receita.setOnClickListener(){
            val view: View = window.decorView
            val viewCriada = LayoutInflater.from(this)
                    .inflate(R.layout.form_transacao,
                            view as ViewGroup,
                            false)

            val ano = 2021
            val mes = 2
            val dia = 4

            val hoje = Calendar.getInstance()
            viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())

            viewCriada.form_transacao_data.setOnClickListener {
                DatePickerDialog(this,
                object : DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        var dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(year, month, dayOfMonth)
                        viewCriada.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                    }
                }, ano,mes, dia).show()
            }

            val adapter = ArrayAdapter.createFromResource(this,
                    R.array.categorias_de_receita,
                    android.R.layout.simple_spinner_dropdown_item)
            viewCriada.form_transacao_categoria.adapter = adapter

            AlertDialog.Builder(this)
                    .setTitle("Título")
                    .setView(viewCriada)
                    .setPositiveButton("Adicionar", null)
                    .setNegativeButton("Cancelar", null)
                    .show()
        }
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