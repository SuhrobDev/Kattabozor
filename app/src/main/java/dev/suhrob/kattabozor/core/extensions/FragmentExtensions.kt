package dev.suhrob.kattabozor.core.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.popBackStack() {
    findNavController().popBackStack()
}

fun Fragment.navigate(id: Int) {
    findNavController().navigate(id)
}

fun Fragment.navigateWithArgs(id: Int, bundle: Bundle) {
    findNavController().navigate(id, bundle)
}
//
//fun objectToJson(data: DataModel): String {
//    return Gson().toJson(data)
//}
//
//fun jsonToObject(data: String?): DataModel {
//    return Gson().fromJson(data ?: "null", DataModel::class.java)
//}