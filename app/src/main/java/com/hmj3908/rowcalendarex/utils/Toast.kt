package com.hmj3908.rowcalendarex.utils

import android.content.Context
import android.widget.Toast

class Toast {


    /**
     * Toast UI
     * @param ctx Context
     * @param text String
     */
    fun toast(ctx: Context?, text: String?) {

        // context && text Null 일 시 Return
        if (ctx == null && text == null) return

        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show()
    }
}