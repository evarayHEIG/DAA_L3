package ch.heigvd.iict.daa.template

import java.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import ch.heigvd.iict.daa.labo3.Person
import ch.heigvd.iict.daa.labo3.Student
import ch.heigvd.iict.daa.labo3.Worker
import ch.heigvd.iict.daa.template.databinding.ActivityMainBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Date

/**
 * @author Rachel Tranchida
 * @author Massimo Stefani
 * @author Eva Ray
 */
class MainActivity : AppCompatActivity() {

    // create a binding object for the layout, used to access its views
    private lateinit var binding: ActivityMainBinding
    private lateinit var datePicker: MaterialDatePicker<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // set the content view to the root view of the binding, instead of the layout
        setContentView(binding.root)
        initDatePicker()
        initializeListeners()
        initSpinners()
    }

    /**
     * Initialize the listeners for the buttons
     */
    private fun initializeListeners() {

        binding.deleteButton.setOnClickListener {
            resetFormData()
        }

        binding.okButton.setOnClickListener{
            submitFormData()
        }

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

        datePickerListener()
    }

    /**
     * Initialize the date picker with the constraints for the date picker
     */
    private fun initDatePicker(){
        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(resources.getString(R.string.main_base_birthdate_dialog_title))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointBackward.now())
                    .setStart(Calendar.getInstance().also { it.add(Calendar.YEAR, -110) }.timeInMillis)
                    .build())
            .build()
    }

    /**
     * Initialize the spinners with the data from the strings.xml file
     */
    private fun initSpinners() {
        binding.baseSpinnerNationality.adapter = PlaceholderSpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf(getString(R.string.nationality_empty)) + resources.getStringArray(R.array.nationalities)
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        binding.specificSpinnerWorkerSector.adapter =PlaceholderSpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf(getString(R.string.sectors_empty)) + resources.getStringArray(R.array.sectors)
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
    }

    /**
     * Add listener to the image button to show the date picker when the user clicks on it
     * Add listener to the positive button of the date picker to set the selected date in the text view
     */
    private fun datePickerListener(){

        // Show the date picker when the user clicks on the cake icon
        binding.baseImageButtonCake.setOnClickListener {
            datePicker.show(supportFragmentManager, "DatePicker")
        }

        // Set the selected date in the text view when the user clicks on the positive button
        datePicker.addOnPositiveButtonClickListener {
            binding.baseEditTextBirthdate.setText(
                Person.dateFormatter.format(Date(Calendar.getInstance().timeInMillis))
            )
        }
    }

    /**
     * Clear all the text fields present in the form
     */
    private fun resetFormData() {
        binding.donneesComplementairesCommentaireChamp.text.clear()
        binding.donneesComplementairesEmailChamp.text.clear()
        binding.baseEditTextName.text.clear()
        binding.baseEditTextSurname.text.clear()
        binding.baseEditTextBirthdate.text.clear()
        resetRadioGroup()
        binding.baseSpinnerNationality.setSelection(0)
        binding.specificEditTextWorkerCompany.text.clear()
        binding.specificEditTextWorkerExperience.text.clear()
        binding.specificSpinnerWorkerSector.setSelection(0)
        binding.specificEditTextStudentSchool.text.clear()
        binding.specificEditTextStudentGraduationYear.text.clear()
    }

    /**
     * Clear all the radio buttons present in the form and hide the specific data layout
     */
    private fun resetRadioGroup(){
        binding.baseRadioGroupOccupation.clearCheck()
        binding.specificWorkerDataLayout.visibility = View.GONE
        binding.specificStudentDataLayout.visibility = View.GONE
    }

    /**
     * Create a Student or a Worker object from the data entered in the form
     */
    private fun submitFormData() {

        if (binding.baseRadioGroupOccupation.checkedRadioButtonId == -1) {
            // Display an error message
            Toast.makeText(this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show()
            return // Exit the function if no selection is made
        }

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
        val student = Student(
            binding.baseEditTextName.text.toString().ifEmpty { "Unknown Name" },
            binding.baseEditTextSurname.text.toString().ifEmpty { "Unknown Surname" },
            Calendar.getInstance(),
            binding.baseSpinnerNationality.selectedItem?.toString() ?: "Unknown Nationality",
            binding.specificEditTextStudentSchool.text.toString().ifEmpty { "Unknown School" },
            binding.specificEditTextStudentGraduationYear.text.toString().toIntOrNull() ?: 0,
            binding.donneesComplementairesEmailChamp.text.toString().ifEmpty { "no-email@example.com" },
            binding.donneesComplementairesCommentaireChamp.text.toString().ifEmpty { "No Comments" }
        )

        println(student)
        resetFormData()
        return student
    }

    /**
     * Create a Worker object from the data entered in the form
     */
    private fun createWorker(): Worker {
        val worker = Worker(
            binding.baseEditTextName.text.toString().ifEmpty { "Unknown Name" },
            binding.baseEditTextSurname.text.toString().ifEmpty { "Unknown Surname" },
            Calendar.getInstance(),
            binding.baseSpinnerNationality.selectedItem?.toString() ?: "Unknown Nationality",
            binding.specificEditTextWorkerCompany.text.toString().ifEmpty { "Unknown Company" },
            binding.specificSpinnerWorkerSector.selectedItem?.toString() ?: "Unknown Sector",
            binding.specificEditTextWorkerExperience.text.toString().toIntOrNull() ?: 0,
            binding.donneesComplementairesEmailChamp.text.toString().ifEmpty { "no-email@example.com" },
            binding.donneesComplementairesCommentaireChamp.text.toString().ifEmpty { "No Comments" }
        )

        println(worker)
        resetFormData()
        return worker
    }


}

