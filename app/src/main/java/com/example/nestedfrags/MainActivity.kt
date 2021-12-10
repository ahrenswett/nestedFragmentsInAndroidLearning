package com.example.nestedfrags

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
var m_text = ""

class MainActivity : AppCompatActivity() {
    private lateinit var pack: Pack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {

            var renderPackButton: Button = findViewById(R.id.button)
            renderPackButton.setText("Add Pack")
            renderPackButton.setOnClickListener{
//                renderPack()
                var frag : AddPackDialogFragment = AddPackDialogFragment()
                frag.show(supportFragmentManager.beginTransaction(),null)
            }
        }
        m_text
    }

    fun renderPack() {
        if (!this::pack.isInitialized){
        // Get pack name from user
            pack = Pack(2)
            addFragment( PackFrag.newInstance(pack.name, pack.avTemp, pack.avVolt) ,R.id.PackFragFragmentContainerView)
        }else{
            showDialog("Pack Name",this)
            pack = Pack(2)
            replaceFragment( PackFrag.newInstance(pack.name, pack.avTemp, pack.avVolt) ,R.id.PackFragFragmentContainerView)
        }
    }
}


/** Edit text dialog from: https://handyopinion.com/show-alert-dialog-with-an-input-field-edittext-in-android-kotlin/ */
fun showDialog(title : String?, context: Context ) {
    val getInputFromUser: AlertDialog.Builder = AlertDialog.Builder(context)
    getInputFromUser.setTitle("Enter $title")
// Set up the input
    val input = EditText(context)
    input.setHint(title)
    input.inputType = InputType.TYPE_CLASS_TEXT
    getInputFromUser.setView(input)
    getInputFromUser.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
        // Here you get get input text from the Edittext
        m_text = input.text.toString()
        Toast.makeText(context,"name is ${m_text}",Toast.LENGTH_SHORT).show()
    })
    getInputFromUser.setNegativeButton(
        "Cancel",
        DialogInterface.OnClickListener { dialog, which -> dialog.cancel()
        })
    getInputFromUser.show()
}

class AddPackDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Set Pack Name")
            val input = EditText(context)
            input.setHint("name")
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)
                .setPositiveButton("Submit",
                    DialogInterface.OnClickListener { dialog, id ->
                        // FIRE ZE MISSILES!
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}


// Add fragments from any activity
/**    https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b **/
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}
