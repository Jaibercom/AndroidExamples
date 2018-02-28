package co.edu.udea.compumovil.startactivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView


class DisplayMessageActivity : AppCompatActivity() {

    val EXTRA_MESSAGE = "co.edu.udea.compumovil.startactivity.MESSAGE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }
    }

}
