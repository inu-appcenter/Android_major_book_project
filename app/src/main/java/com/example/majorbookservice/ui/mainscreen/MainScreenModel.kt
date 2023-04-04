package com.example.majorbookservice.ui.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.majorbookservice.Data.DTO.Filter
import com.example.majorbookservice.Data.DTO.Subject
import com.example.majorbookservice.Data.Event
import com.example.majorbookservice.Data.Repository
import java.lang.reflect.Member

class MainScreenModel: androidx.lifecycle.ViewModel() {
    private var _setList = MutableLiveData<Event<ArrayList<Subject>>>()
    val setList: LiveData<Event<ArrayList<Subject>>> = _setList

    fun getSubjects(filter: Filter) {
        Repository().getSubjects(filter.professorName, filter.departmentName, filter.subjectName,
        object: Repository.GetDataCallback<ArrayList<Subject>> {
            override fun onSuccess(data: ArrayList<Subject>?) {
                if (data != null) {
                    //subject.postValue(Event(data.SubjectList))
                    _setList.postValue(Event(data))
                    Log.d("retrofit_Model", "success")
                    Log.d("retrofit_Model", data.toString())
                    //outData.addAll(data.SubjectList)
                }
            }

            override fun onFailure(throwable: Throwable) {
                Log.d("retrofit", "failed")
            }

        })
    }
}