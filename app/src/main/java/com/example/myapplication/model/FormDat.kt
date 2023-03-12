package com.example.myapplication.model

import com.example.myapplication.model.validation.ValidationResults
import java.time.Year

class FormDat(private var studentId:String,
              private var year: String,
              private var semester:String,
              private var agree:Boolean
              ) {
    fun validateStudentID():ValidationResults{
        return if(studentId.isEmpty()){
            ValidationResults.Empty("StudentId is empty")
        }
        else if(studentId.length!=10){
            ValidationResults.Invalid("ID should have 10 digits")
        }
        else if(!studentId.startsWith("IT",true)){
            ValidationResults.Invalid("IT needs to be in front")
        }
        else
            ValidationResults.Valid
    }
    fun validateYear():ValidationResults {
       return if (year.isEmpty()) {
            ValidationResults.Empty("Semester is empty")
        } else {
            ValidationResults.Valid
        }
    }

    fun validateSemester():ValidationResults {
        return  if (semester.isEmpty()) {
            ValidationResults.Empty("Semester is empty")
        } else {
            ValidationResults.Valid
        }
    }


    fun validateAgreement():ValidationResults{
        return if(!agree){
            ValidationResults.Invalid("agree for the terms")
        }
        else{
            ValidationResults.Valid
        }
    }

}