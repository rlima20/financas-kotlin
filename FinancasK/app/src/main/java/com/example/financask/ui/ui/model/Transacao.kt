package com.example.financask.ui.ui.model
import java.math.BigDecimal
import java.util.*

/**
 * Eu deixando val data: Calendar = Calendar.getInstance() - Estou dizendo que
 * se no momento de eu instanciar a classe Transação eu não colocar uma data
 * como 4° parâmetro, o valor padrão vai ser o Calendar.getInstance()
 *
 * Eu posso também constrir um construtor secundário que é o que fica dentro
 * do corpo da classe. O construtor primário sempre tem que ser chamado
 * independemente de eu ter um secundário
 *
 * Podemos utilizar uma feature chamada namedParameter
 */
class Transacao(val valor: BigDecimal,
                val categoria: String = "Indefinida",
                val tipo: Tipo,
                val data: Calendar = Calendar.getInstance()){
}