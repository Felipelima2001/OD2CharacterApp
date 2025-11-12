package com.example.od2characterapp.domain.model

enum class RacaType(val display: String, val movimento: Int, val infravisao: Int, val alinhamento: String) {
    HUMANO("Humano", 9, 0, "Qualquer"),
    ELFO("Elfo", 9, 18, "Tende a neutralidade"),
    ANAO("Anão", 6, 18, "Tende à ordem"),
    HALFLING("Halfling", 9, 0, "Tende à paz")
}