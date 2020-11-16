package me.vponomarenko.letmethink.extension

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.inputmethod.InputMethodManager
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.utils.ViewModelFactory

/**
 * Author: Valery Ponomarenko
 * Date: 04/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

fun Fragment.prepareToolbar(toolbar: Toolbar, onBackClick: (() -> Unit)? = null) {
    (activity as? AppCompatActivity)?.apply {
        setSupportActionBar(toolbar)
        onBackClick?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            toolbar.setNavigationOnClickListener {
                onBackClick()
            }
        }
    }
}

fun Fragment.prepareDrawer(toolbar: Toolbar) {
    (activity as? AppCompatActivity)?.apply {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }
}

fun Fragment.hideKeyboard() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    view?.let {
        imm?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Fragment.showKeyboard() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Fragment.alert(
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes okButtonText: Int,
    @StringRes cancelButtonText: Int,
    onSuccess: (() -> Unit)? = null,
    onCancel: (() -> Unit)? = null
) {
    AlertDialog.Builder(this.requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(okButtonText, { dialog, _ ->
            onSuccess?.invoke()
            dialog.dismiss()
        })
        .setNegativeButton(cancelButtonText, { dialog, _ ->
            onCancel?.invoke()
            dialog.dismiss()
        })
        .show()
}

inline fun <reified VM : ViewModel> Fragment.provideViewModel(crossinline factoryProvider: () -> ViewModelFactory) =
    lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, factoryProvider()).get(VM::class.java)
    }