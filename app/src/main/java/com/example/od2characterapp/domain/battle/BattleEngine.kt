package com.example.od2characterapp.domain.battle

import kotlin.random.Random

class BattleEngine {

    private fun rollD20() = Random.nextInt(1, 21)
    private fun rollD8() = Random.nextInt(1, 9)

    fun executeTurn(playerHp: Int, enemyHp: Int): BattleResult {

        var pHp = playerHp
        var eHp = enemyHp

        // Player ataca
        val acertoJogador = rollD20()
        if (acertoJogador >= 10) {
            eHp -= rollD8()
        }

        // Enemy ataca
        if (eHp > 0) {
            val acertoInimigo = rollD20()
            if (acertoInimigo >= 10) {
                pHp -= rollD8()
            }
        }

        return BattleResult(
            playerHp = pHp.coerceAtLeast(0),
            enemyHp = eHp.coerceAtLeast(0),
            playerDead = pHp <= 0
        )
    }
}
