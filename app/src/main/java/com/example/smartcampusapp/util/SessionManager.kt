package com.example.smartcampusapp.util

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object {
        const val KEY_IS_LOGGED_IN = "is_logged_in"
        const val KEY_STUDENT_ID = "student_id"
    }

    fun saveSession(studentId: String) {
        val editor = prefs.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_STUDENT_ID, studentId)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getStudentId(): String {
        return prefs.getString(KEY_STUDENT_ID, "") ?: ""
    }

    fun clearSession() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
}