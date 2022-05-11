package com.example.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

class PreferencesDatastore
    @Inject constructor(@ApplicationContext private val context:Context) {

   private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "KeyPreferences")

    suspend fun saveData(access_key:String, access_value:String){
        val dataStoreKey = stringPreferencesKey(access_key)
        context.datastore.edit { KeyPreferences ->
            KeyPreferences[dataStoreKey] = access_value
        }
    }

    suspend fun readData(access_key:String):String? {
        val dataStoreKey = stringPreferencesKey(access_key)
        return context.datastore.data.first()[dataStoreKey].toString()
    }
}