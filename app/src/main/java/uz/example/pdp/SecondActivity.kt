package uz.example.pdp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.basic.R

class SecondActivity : AppCompatActivity() {
    private lateinit var buttonPlay: Button
    lateinit var buttonAbout: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        buttonPlay = findViewById(R.id.playBtn)
        buttonAbout = findViewById(R.id.aboutBtn)
        buttonPlay.setOnClickListener {
            startActivity(Intent(this, MathGameActivity::class.java))
        }
        buttonAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java)) }
    }
}