package uz.example.pdp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import com.example.basic.R
import com.sackcentury.shinebuttonlib.ShineButton
import com.sdsmdg.tastytoast.TastyToast
import kotlinx.android.synthetic.main.activity_math_game.*
import java.util.*

class MathGameActivity : AppCompatActivity() {
    private lateinit var shineButton: ShineButton
    private lateinit var random: Random
    private lateinit var count: TextView
    private lateinit var number1: TextView
    private lateinit var number2: TextView
    private lateinit var amal: TextView
    private lateinit var result: EditText
    private var result1: String? = null
    private var countResult: Int = 0
    private lateinit var countDownTimer: CountDownTimer
    private var noCountResult: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_game)
        random = Random()
        TastyToast.makeText(
            this,
            "O'yin boshlandi sizga har bitta savol uchun 20 sekund vaqt beriladi omad...",
            TastyToast.LENGTH_LONG,
            TastyToast.INFO
        )
        findById()
        math()
        timeCount()
        shineButton.setOnCheckStateChangeListener { _, _ ->
            //Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show()

            val myResult = result.text.toString()
            if (!TextUtils.isEmpty(myResult)) {
                if (myResult == result1.toString()) {
                    TastyToast.makeText(
                        this,
                        "Siz to'g'ri topdingiz",
                        TastyToast.LENGTH_LONG,
                        TastyToast.SUCCESS
                    )
                    countResult++
                } else {
                    TastyToast.makeText(
                        this,
                        "Siz noto'g'ri topdingiz...",
                        TastyToast.LENGTH_LONG,
                        TastyToast.ERROR
                    )
                    noCountResult++
                }
//                math()
//                timeCount()
            } else {
                noCountResult++
                TastyToast.makeText(
                    this,
                    "Siz javobni kiritmadingiz...",
                    TastyToast.LENGTH_LONG,
                    TastyToast.DEFAULT
                )
            }
            result.setText("")
            math()
            countDownTimer.cancel()
            timeCount()
        }

    }

    private fun timeCount() {
        countDownTimer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                noCountResult++
                noCount.text = noCountResult.toString()
                startActivity(Intent(this@MathGameActivity, SecondActivity::class.java))
                TastyToast.makeText(
                    this@MathGameActivity,
                    "Siz o'yinni yakunladingiz siz ${countResult + noCountResult} ta savoldan $countResult ta to'g'ri topdingiz...",
                    TastyToast.LENGTH_LONG,
                    TastyToast.SUCCESS
                )
                finish()
            }

        }.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    private fun findById() {
        shineButton = findViewById(R.id.imageButton)
        count = findViewById(R.id.count)
        number1 = findViewById(R.id.number1)
        number2 = findViewById(R.id.number2)
        amal = findViewById(R.id.amal)
        result = findViewById(R.id.result)
    }


    private fun math() {

        count.text = countResult.toString()
        noCount.text = noCountResult.toString()
        val randomNumber1 = random.nextInt(50)
        val randomNumber2 = random.nextInt(50)
        number1.text = randomNumber1.toString()
        number2.text = randomNumber2.toString()
        result1 = null

        when (random.nextInt(4)) {
            0 -> {
                amal.text = "+"
                result1 = (randomNumber1 + randomNumber2).toString()

            }
            1 -> {
                amal.text = "-"
                result1 = (randomNumber1 - randomNumber2).toString()
            }
            2 -> {
                amal.text = "*"
                result1 = (randomNumber1 * randomNumber2).toString()
            }
            3 -> {
                amal.text = "/"

                if (randomNumber2 == 0) {
                    TastyToast.makeText(
                        this,
                        "0 ga bo'lish mumkun emas!!!",
                        TastyToast.LENGTH_LONG,
                        TastyToast.ERROR
                    )
                } else {
                    result1 = if (randomNumber1 > randomNumber2) {
                        (randomNumber1 / randomNumber2).toString()
                    } else (randomNumber1 / randomNumber2).toString()
                }
            }
        }
    }

/*
    fun secondMath() {
        count.text = countResult.toString()
        noCount.text = noCountResult.toString()
        val randomNumber1 = random.nextInt(50)
        val randomNumber2 = random.nextInt(50)
        number1.text = randomNumber1.toString()
        number2.text = randomNumber2.toString()
        result1 = null

        when (random.nextInt(4)) {
            0 -> {
                amal.text = "+"
                result1 = (randomNumber1 + randomNumber2).toString()

            }
            1 -> {
                amal.text = "-"
                result1 = (randomNumber1 - randomNumber2).toString()
            }
            2 -> {
                amal.text = "*"
                result1 = (randomNumber1 * randomNumber2).toString()
            }
            3 -> {
                amal.text = "/"

                if (randomNumber2 == 0) {
                    TastyToast.makeText(
                        this,
                        "0 ga bo'lish mumkun emas!!!",
                        TastyToast.LENGTH_LONG,
                        TastyToast.ERROR
                    )
                } else {
                    result1 = if (randomNumber1 > randomNumber2) {
                        (randomNumber1 / randomNumber2).toString()
                    } else (randomNumber1 / randomNumber2).toString()
                }
            }
        }

    }
 */

}