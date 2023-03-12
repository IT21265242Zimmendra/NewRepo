package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.model.FormDat
import com.example.myapplication.model.FormData
import com.example.myapplication.model.validation.ValidationResults

class MainActivity : AppCompatActivity() {

    lateinit var edtStudentId:EditText
    lateinit var spnYear:Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgree:CheckBox
    lateinit var btnSubmit:Button
    private var count =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtStudentId=findViewById(R.id.studentId)
        spnYear =findViewById(R.id.year)
        spnSemester = findViewById(R.id.sem)
        cbAgree = findViewById(R.id.cbCondition)
        btnSubmit = findViewById(R.id.submit)


    }
    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") {
                dialog, which ->         // Do something when the "OK" button is clicked
        }
        val dialog = builder.create()
        dialog.show()
    }
    override fun onResume(){
        super.onResume()
        btnSubmit.setOnClickListener(View.OnClickListener {
            count =0
            val myForm =FormDat(edtStudentId.text.toString(),spnYear.selectedItem.toString(),
                spnSemester.selectedItem.toString(),cbAgree.isChecked)

            val studentIDvalidation=myForm.validateStudentID()
            val yearValidation=myForm.validateYear()
            val semesterValidation =myForm.validateSemester()
            val agreeValidation = myForm.validateAgreement()

            when(studentIDvalidation){
                is ValidationResults.Empty->edtStudentId.error=studentIDvalidation.errorMessage

                is ValidationResults.Invalid ->edtStudentId.error=studentIDvalidation.errorMessage

                is ValidationResults.Valid ->
                    count++;


            }
            when(yearValidation){
                is ValidationResults.Empty-> {
                    val tv :TextView=spnYear.selectedView as TextView
                    tv.error
                    tv.text = yearValidation.errorMessage
                }
                is ValidationResults.Invalid -> {
                    val tv :TextView=spnYear.selectedView as TextView
                    tv.error
                    tv.text = yearValidation.errorMessage
                }
                is ValidationResults.Valid ->
                    count++;
            }
            when(semesterValidation){
                is ValidationResults.Empty-> {
                    val tv :TextView=spnSemester.selectedView as TextView
                    tv.error
                    tv.text = semesterValidation.errorMessage
                }
                is ValidationResults.Invalid -> {
                    val tv :TextView=spnSemester.selectedView as TextView
                    tv.error
                    tv.text = semesterValidation.errorMessage
                }
                is ValidationResults.Valid ->
                    count++;
            }
            when(agreeValidation){
                is ValidationResults.Empty-> {
                   displayAlert("Error",agreeValidation.errorMessage)
                }
                is ValidationResults.Invalid -> {

                }
                is ValidationResults.Valid ->
                    count++;
            }
            if(count==4){
                displayAlert("Success","You have registered")
                val dataObject=FormData(edtStudentId.text.toString(),
                Integer.parseInt(spnYear.selectedItem.toString()),
                spnSemester.selectedItem.toString(),
                cbAgree.isChecked
                )
            }
        })
    }


}