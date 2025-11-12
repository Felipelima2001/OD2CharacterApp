package com.example.od2characterapp.domain.util


import com.example.od2characterapp.domain.model.Atributos
import kotlin.random.Random

object AtributosGenerator {
    private fun roll(n: Int, sides: Int = 6) = List(n) { Random.nextInt(1, sides + 1) }

    fun classica(): Atributos {
        fun r() = roll(3).sum()
        return Atributos(r(), r(), r(), r(), r(), r())
    }

    fun heroica(): Atributos {
        fun r() = roll(4).sortedDescending().take(3).sum()
        return Atributos(r(), r(), r(), r(), r(), r())
    }

    // aventureiro: aqui gera valores que a UI pode permitir rearranjar manualmente
    fun aventureiro(): Atributos {
        // Exemplo: valores fortes e médios (pode ajustar)
        return Atributos(15, 14, 13, 12, 10, 8)
    }
}