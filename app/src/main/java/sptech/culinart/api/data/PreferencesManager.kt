package sptech.culinart.api.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager private constructor(context: Context) {
    private val prefName = "Informacoes Usuario"
    private val keyToken = "token"
    private val keyUserId = "idUsuario"
    private val keyName = "nome"
    private val keyEmail = "email"
    private val keyPermission = "permissao"
    private val keyIsAtivo = "isAtivo"

    private val preferences: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var instance: PreferencesManager? = null

        fun getInstance(context: Context): PreferencesManager {
            return instance ?: synchronized(this) {
                instance ?: PreferencesManager(context).also { instance = it }
            }
        }
    }

    fun saveToken(token: String) {
        preferences.edit().putString(keyToken, token).apply()
    }

    fun getToken(): String? {
        return preferences.getString(keyToken, null)
    }

    fun saveUserId(userId: Int) {
        preferences.edit().putInt(keyUserId, userId).apply()
    }

    fun getUserId(): Int {
        return preferences.getInt(keyUserId, 0)
    }

    fun saveName(name: String) {
        preferences.edit().putString(keyName, name).apply()
    }

    fun getName(): String? {
        return preferences.getString(keyName, null)
    }

    fun saveEmail(email: String) {
        preferences.edit().putString(keyEmail, email).apply()
    }

    fun getEmail(): String? {
        return preferences.getString(keyEmail, null)
    }

    fun savePermission(hasPermission: String) {
        preferences.edit().putString(keyPermission, hasPermission).apply()
    }

    fun getPermission(): String? {
        return preferences.getString(keyPermission, null)
    }

    fun saveIsAtivo(isAtivo: String) {
        preferences.edit().putString(keyIsAtivo, isAtivo).apply()
    }

    fun getIsAtivo(): String? {
        return preferences.getString(keyIsAtivo, null)
    }

    fun clearData() {
        preferences.edit().remove(keyToken).remove(keyUserId).remove(keyName).remove(keyEmail)
            .remove(keyPermission).remove(keyIsAtivo).apply()
    }
}
