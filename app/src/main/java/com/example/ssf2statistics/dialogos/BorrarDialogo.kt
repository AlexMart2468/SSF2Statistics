package com.example.ssf2statistics.dialogos

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.ssf2statistics.R

class BorrarDialogo : DialogFragment() {
    private lateinit var borrarListener: BorrarListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            borrarListener = parentFragment as BorrarListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement BorrarListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de borrar?")
                .setPositiveButton("Borrar") { _, _ ->
                    borrarListener.borrarPersonaje()
                }
                .setNegativeButton("Cancelar", null)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.borrar_personaje)
                .setPositiveButton(R.string.yes,
                    DialogInterface.OnClickListener  { dialog, id ->
                        borrarListener.borrarPersonaje()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }*/

    interface BorrarListener{
        fun borrarPersonaje()
    }
}