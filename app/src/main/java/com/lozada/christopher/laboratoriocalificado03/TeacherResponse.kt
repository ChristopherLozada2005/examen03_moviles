package com.lozada.christopher.laboratoriocalificado03

data class TeacherResponse(
    val name: String,
    val last_name: String,
    val phone_number: String,
    val email: String,
    val image_url: String
) {
    fun getFullName(): String = "$name $last_name"
}
