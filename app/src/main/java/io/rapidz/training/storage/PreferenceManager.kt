package io.rapidz.training.storage

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {

    private const val PREFERENCES_KEY = "PREFERENCE_MANAGER"

    const val LAST_LOGIN_USERNAME = "LAST_LOGIN_USERNAME"
    const val LAST_LOGIN_PASSWORD = "LAST_LOGIN_PASSWORD"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    fun <T> writeToPreferences(key: String, value: T) {
        val editor = preferences.edit()
        when (value) {
            is String -> editor.putString(key, value as String)
            is Int -> editor.putInt(key, value as Int)
            is Long -> editor.putLong(key, value as Long)
            is Boolean -> editor.putBoolean(key, value as Boolean)
            is Float -> editor.putFloat(key, value as Float)
            else -> throw IllegalArgumentException("Type not supported for preferences")
        }
        editor.apply()
    }

    inline fun <reified T> readFromPreferences(key: String): T {
        return when (T::class) {
            String::class -> preferences.getString(key, "") as T
            Int::class -> preferences.getInt(key, 0) as T
            Long::class -> preferences.getLong(key, 0L) as T
            Boolean::class -> preferences.getBoolean(key, false) as T
            Float::class -> preferences.getFloat(key, 0.0f) as T
            else -> throw IllegalArgumentException("Type not supported for preferences")
        }
    }
}