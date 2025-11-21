package com.example.od2characterapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.content.ContextCompat
import com.example.od2characterapp.App
import com.example.od2characterapp.domain.battle.BattleEngine
import kotlinx.coroutines.*

class BattleService : Service() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val engine = BattleEngine()

    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createChannel(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val personagemId = intent?.getIntExtra("id", -1)
        if (personagemId == -1) stopSelf()

        startForeground(
            1,
            NotificationHelper.buildForegroundNotification(this)
        )

        scope.launch {

            val dao = App.database.personagemDao()
            var personagem = dao.findById(personagemId!!)
            var enemyHp = 20

            while (personagem != null && personagem.currentHp > 0) {

                delay(2000)

                val result = engine.executeTurn(personagem.currentHp, enemyHp)

                dao.updateHp(personagem.id, result.playerHp)

                enemyHp = result.enemyHp

                if (result.playerDead) {
                    NotificationHelper.showDeathNotification(this@BattleService, personagem)
                    stopSelf()
                    break
                }

                personagem = dao.findById(personagemId)
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
