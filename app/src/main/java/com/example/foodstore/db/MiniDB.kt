package com.example.foodstore.db


import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import com.example.foodstore.model.PopularFoodModel
import com.google.gson.Gson
import java.io.File
import java.util.Arrays


class MiniDB(appContext: Context) {
    private val preferences: SharedPreferences
    private var DEFAULT_APP_IMAGEDATA_DIRECTORY: String? = null

    var savedImagePath = ""
        private set

    init {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext)
    }
//
//    fun getImage(path: String?): Bitmap? {
//        var bitmapFromPath: Bitmap? = null
//        try {
//            bitmapFromPath = BitmapFactory.decodeFile(path)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return bitmapFromPath
//    }
//
//    fun putImage(theFolder: String?, theImageName: String?, theBitmap: Bitmap?): String? {
//        if (theFolder == null || theImageName == null || theBitmap == null) return null
//        DEFAULT_APP_IMAGEDATA_DIRECTORY = theFolder
//        val mFullPath = setupFullPath(theImageName)
//        if (mFullPath != "") {
//            savedImagePath = mFullPath
//            saveBitmap(mFullPath, theBitmap)
//        }
//        return mFullPath
//    }
//
//    fun putImageWithFullPath(fullPath: String?, theBitmap: Bitmap?): Boolean {
//        return !(fullPath == null || theBitmap == null) && saveBitmap(fullPath, theBitmap)
//    }

//    private fun setupFullPath(imageName: String): String {
//        val mFolder =
//            File(Environment.getExternalStorageDirectory(), DEFAULT_APP_IMAGEDATA_DIRECTORY)
//        if (isExternalStorageReadable && isExternalStorageWritable && !mFolder.exists()) {
//            if (!mFolder.mkdirs()) {
//                Log.e("ERROR", "Failed to setup folder")
//                return ""
//            }
//        }
//        return mFolder.path + '/' + imageName
//    }

    fun getString(key: String?): String? {
        return preferences.getString(key, "")
    }

    fun getListString(key: String?): ArrayList<String> {
        return ArrayList(Arrays.asList(*TextUtils.split(preferences.getString(key, ""), "‚‗‚")))
    }

    fun getListObject(key: String): MutableList<PopularFoodModel> {
        val gson = Gson()
        val objStrings = getListString(key)
        val playerList: MutableList<PopularFoodModel> = ArrayList()
        for (jObjString in objStrings) {
            val player: PopularFoodModel = gson.fromJson(jObjString, PopularFoodModel::class.java)
            playerList.add(player)
        }
        return playerList
    }

//    fun <T> getObject(key: String, classT: Class<T>): T {
//        val json = getString(key)
//        return Gson().fromJson(json, classT) ?: throw NullPointerException()

    fun putString(key: String, value: String) {
        checkForNullKey(key)
        checkForNullValue(value)
        preferences.edit().putString(key, value).apply()
    }

    fun putListString(key: String, stringList: ArrayList<String>) {
        checkForNullKey(key)
        val myStringList = stringList.toTypedArray()
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply()
    }

//
//    fun putObject(key: String, obj: Any?) {
//        checkForNullKey(key)
//        val gson = Gson()
//        putString(key, gson.toJson(obj))
//    }

    fun putListObject(key: String, playerList: MutableList<PopularFoodModel>) {
        checkForNullKey(key)
        val gson = Gson()
        val objStrings = ArrayList<String>()
        for (player in playerList) {
            objStrings.add(gson.toJson(player))
        }
        putListString(key, objStrings)
    }

    fun removeFood(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun deleteImage(path: String): Boolean {
        return File(path).delete()
    }

    fun clear() {
        preferences.edit().clear().apply()
    }

    private fun checkForNullKey(key: String?) {
        if (key == null) {
            throw NullPointerException()
        }
    }

    private fun checkForNullValue(value: String?) {
        if (value == null) {
            throw NullPointerException()
        }
    }

}