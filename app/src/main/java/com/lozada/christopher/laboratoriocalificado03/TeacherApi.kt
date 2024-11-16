package com.lozada.christopher.laboratoriocalificado03

import retrofit2.http.GET

interface TeacherApi {
    @GET("list/teacher-b")
    suspend fun getTeachers(): TeacherListResponse
}
