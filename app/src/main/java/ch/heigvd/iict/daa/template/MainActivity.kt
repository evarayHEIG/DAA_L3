package ch.heigvd.iict.daa.template

import java.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
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
    }

    /**
     * Initialize the listeners for the buttons
     */
    private fun initializeListeners() {

        binding.baseRadioGroupOccupation.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.base_radio_button_student -> {
                    binding.specificStudentDataLayout.visibility = View.VISIBLE
                    binding.specificWorkerDataLayout.visibility = View.GONE
                    val constraintLayout = (binding.root).getChildAt(0) as ConstraintLayout
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(constraintLayout)
                    constraintSet.connect(
                        binding.donneesComplementairesTitre.id,
                        ConstraintSet.TOP,
                        binding.specificEditTextStudentGraduationYear.id,
                        ConstraintSet.BOTTOM,
                        0
                    )
                    constraintSet.applyTo(constraintLayout)
                }
                R.id.base_radio_button_worker -> {
                    binding.specificStudentDataLayout.visibility = View.GONE
                    binding.specificWorkerDataLayout.visibility = View.VISIBLE

                    val constraintLayout = (binding.root).getChildAt(0) as ConstraintLayout
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(constraintLayout)

                    constraintSet.connect(
                        binding.donneesComplementairesTitre.id,
                        ConstraintSet.TOP,
                        binding.specificEditTextWorkerExperience.id,
                        ConstraintSet.BOTTOM,
                        0
                    )
                    constraintSet.applyTo(constraintLayout)
                }
            }
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

