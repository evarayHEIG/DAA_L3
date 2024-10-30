package ch.heigvd.iict.daa.template

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.green_activity)

        val studentLayout = findViewById<LinearLayout>(R.id.student_data_layout)
        val workerLayout = findViewById<LinearLayout>(R.id.worker_data_layout)
        val radioGroup = findViewById<RadioGroup>(R.id.base_radio_group_occupation)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.base_radio_button_student -> {
                    studentLayout.visibility = View.VISIBLE
                    workerLayout.visibility = View.GONE
                }
                R.id.base_radio_button_worker -> {
                    studentLayout.visibility = View.GONE
                    workerLayout.visibility = View.VISIBLE
                }
            }
        }
    }
}