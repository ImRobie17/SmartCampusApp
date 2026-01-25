package com.example.smartcampusapp.util

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_LOGGED_IN, false)

    fun saveSession(studentId: String) {
        prefs.edit()
            .putBoolean(KEY_LOGGED_IN, true)
            .putString(KEY_STUDENT_ID, studentId)
            .apply()
    }

    fun getStudentId(): String = prefs.getString(KEY_STUDENT_ID, "") ?: ""

    fun clearSession() {
        prefs.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "smartcampus_session"
        private const val KEY_LOGGED_IN = "logged_in"
        private const val KEY_STUDENT_ID = "student_id"
    }
}
