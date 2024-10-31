package ch.heigvd.iict.daa.template

import java.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import ch.heigvd.iict.daa.labo3.Student
import ch.heigvd.iict.daa.labo3.Worker
import ch.heigvd.iict.daa.template.databinding.ActivityMainBinding
import ch.heigvd.iict.daa.template.databinding.GreenActivityBinding
import ch.heigvd.iict.daa.template.databinding.RedLayoutBinding


class MainActivity : AppCompatActivity() {

    // create a binding object for the layout, used to access its views
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // set the content view to the root view of the binding, instead of the layout
        setContentView(binding.root)

        initializeListeners()

        /*val studentLayout = findViewById<LinearLayout>(R.id.specific_student_data_layout)
        val workerLayout = findViewById<LinearLayout>(R.id.specific_worker_data_layout)
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
        }*/
    }

    /**
     * Initialize the listeners for the buttons
     */
    private fun initializeListeners() {

        binding.deleteButton.setOnClickListener {
            cancelFormData()
        }
        binding.okButton.setOnClickListener {
            submitFormData()
        }
    }


    /**
     * Clear all the text fields present in the form
     */
    private fun cancelFormData() {
        binding.donneesComplementairesCommentaireChamp.text.clear()
        binding.donneesComplementairesEmailChamp.text.clear()
    }

    /**
     * Create a Student or a Worker object from the data entered in the form
     */
    private fun submitFormData() {
        if (binding.baseRadioGroupOccupation.checkedRadioButtonId == R.id.base_radio_button_student) {
            createStudent()
        } else {
            createWorker()
        }
    }

    /**
     * Create a Student object from the data entered in the form
     */
    private fun createStudent(): Student {
        return Student("",
            "",
            Calendar.getInstance(),
            "",
            "",
            2024,
            binding.donneesComplementairesEmailChamp.text.toString(),
            binding.donneesComplementairesCommentaireChamp.text.toString())
    }

    /**
     * Create a Worker object from the data entered in the form
     */
    private fun createWorker(): Worker {
        return Worker("",
            "",
            Calendar.getInstance(),
            "",
            "",
            "",
            0,
            binding.donneesComplementairesEmailChamp.text.toString(),
            binding.donneesComplementairesCommentaireChamp.text.toString())
    }
}

