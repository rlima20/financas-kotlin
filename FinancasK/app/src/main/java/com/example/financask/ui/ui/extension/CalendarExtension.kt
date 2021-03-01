package com.example.financask.ui.ui.extension
import java.text.SimpleDateFormat
import java.util.*

/**
 * O Kotlin também atende ao paradigma funcional.
 * Eu não preciso ter sempre classes e fazer a instancia dessas classes.
 * Eu posso simplesmente ter um arquivo com uma função qualquer.
 *
 * Nesse caso declarei uma função. Essa função usa o recurso extension function
 * Dessa forma, indicamos que essa função trata-se de uma extensão da classe Calendar.
 * Ela retorna uma String
 *
 * Aqui nessa classe eu faço uma extension function onde eu pego uma classe nativa
 * como por exemplo a classe Calendar e dou um novo significado a ela, uma nova funcão. Então
 * ela já não tem o mesmo comportamento de antes. Aqui estou extendendo a função formataParaBrasileiro
 * à classe Calendar. Estou dizendo também que essa função retorna uma string.
 * formatoBrasileiro = "dd/MM/yyyy" -> SampleDateFormat e então retorna format. format(this.time.
 */

fun Calendar.formataParaBrasileiro() : String{
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}

