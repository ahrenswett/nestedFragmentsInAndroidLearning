package com.example.nestedfrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val NAME = "name"
private const val VALUE = "value"

/**
 *
 * A simple [Fragment] subclass.
 * Use the [ValueHolder.newInstance] factory method to
 * create an instance of this fragment.
 */
class ValueHolder : Fragment() {
    // TODO: Rename and change types of parameters
    private var name: String? = "Value"
    private var value = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            value = it.getFloat(VALUE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_value_holder, container, false)
        val nameOfHeldValue : TextView = view.findViewById(R.id.nameOfHeldValue)
        nameOfHeldValue.setText(name)
        val valueHeld : TextView = view.findViewById(R.id.value)
        valueHeld.setText(value.toString())
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param name Parameter 1.
         * @param value Parameter 2.
         * @return A new instance of fragment ValueHolder.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, value: Float) =
            ValueHolder().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                    putFloat(VALUE, value)
                }
            }
    }
}