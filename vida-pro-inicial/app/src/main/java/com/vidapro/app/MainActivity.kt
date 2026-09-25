package com.vidapro.app

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val bg = Color.rgb(15, 23, 42)
    private val card = Color.rgb(30, 41, 59)
    private val accent = Color.rgb(34, 197, 94)
    private val white = Color.WHITE
    private val muted = Color.rgb(203, 213, 225)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun showHome() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 18)
            setBackgroundColor(bg)
        }

        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        scroll.addView(content)

        val title = text("VIDA PRO", 30f, white)
        title.gravity = Gravity.CENTER
        content.addView(title, lp( -1, 70))

        val subtitle = text("Tu carrera. Tu equipo. Tu historia.", 16f, muted)
        subtitle.gravity = Gravity.CENTER
        content.addView(subtitle, lp(-1, 45))

        val coins = text("🪙  1.000 monedas", 18f, white)
        coins.gravity = Gravity.CENTER
        coins.setBackgroundColor(card)
        content.addView(coins, lp(-1, 60))

        content.addView(section("MI EQUIPO"))
        content.addView(action("⚽  Mi equipo", "Plantel, formación y entrenamiento") {
            toast("Próximamente: gestión completa del equipo")
        })
        content.addView(action("🏆  Ligas", "Compite durante toda la temporada") {
            toast("Próximamente: ligas y tabla de posiciones")
        })
        content.addView(action("📅  Partidos", "Partidos y horarios en tiempo real") {
            toast("Próximamente: calendario de partidos")
        })
        content.addView(action("🪙  Tienda", "Monedas y contenido del juego") {
            toast("Próximamente: tienda de Vida Pro")
        })

        content.addView(section("PRÓXIMO OBJETIVO"))
        val objective = TextView(this).apply {
            text = "Clasificar a la próxima fecha de liga\n\nNivel del equipo: 1   •   Energía: 100%"
            textSize = 16f
            setTextColor(white)
            setPadding(20, 20, 20, 20)
            setBackgroundColor(card)
        }
        content.addView(objective, lp(-1, 125))

        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))

        val footer = text("Vida Pro • versión inicial", 13f, muted)
        footer.gravity = Gravity.CENTER
        root.addView(footer, lp(-1, 35))

        setContentView(root)
    }

    private fun section(label: String): TextView =
        text(label, 14f, accent).apply {
            setPadding(4, 28, 4, 10)
        }

    private fun action(title: String, desc: String, click: () -> Unit): LinearLayout {
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 15, 20, 15)
            setBackgroundColor(card)
            isClickable = true
            isFocusable = true
            setOnClickListener { click() }
        }
        box.addView(text(title, 19f, white), lp(-1, 34))
        box.addView(text(desc, 14f, muted), lp(-1, 30))
        val params = lp(-1, 78)
        params.setMargins(0, 0, 0, 12)
        box.layoutParams = params
        return box
    }

    private fun text(value: String, size: Float, color: Int) =
        TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
        }

    private fun lp(w: Int, h: Int) = LinearLayout.LayoutParams(w, h)

    private fun toast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
