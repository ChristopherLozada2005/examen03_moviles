package com.lozada.christopher.laboratoriocalificado03

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val teachers = MutableLiveData<List<TeacherResponse>>()

    fun loadTeachersFromApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(TeacherApi::class.java)

        viewModelScope.launch {
            try {
                val response = api.getTeachers()
                teachers.postValue(response.teachers)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
