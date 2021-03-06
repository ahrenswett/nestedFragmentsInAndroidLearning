package com.example.nestedfrags

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val NAME = "Name"
private const val TEMP = "Temp"
private const val VOLTS = "Volts"


/**
 * A simple [Fragment] subclass.
 * Use the [PackFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class PackFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var name: String? = null
    private lateinit var tempHolder: ValueHolder
    private lateinit var voltsHolder: ValueHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            tempHolder = arguments?.let { ValueHolder.newInstance("Temp", it.getFloat(TEMP)) }!!
            voltsHolder = arguments?.let { ValueHolder.newInstance("Volt", it.getFloat(VOLTS)) }!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_pack, container, false)
        this.parentFragmentManager.beginTransaction().add(R.id.voltHolder, voltsHolder).commit()
        this.parentFragmentManager.beginTransaction().add(R.id.tempHolder, tempHolder).commit()
        var packName : TextView? = view.findViewById(R.id.packName)
        packName?.setText(name)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param name Parameter 1.
         * @param temp Parameter 2.
         * @return A new instance of fragment PackFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String?, temp: Float, volts :Float ) =
            PackFrag().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                    putFloat(TEMP, temp)
                    putFloat(VOLTS, volts)
                }
            }
    }
}