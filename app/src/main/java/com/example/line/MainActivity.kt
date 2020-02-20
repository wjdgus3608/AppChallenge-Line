package com.example.line

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.line.databinding.InputUrlDialogBinding
import com.example.line.ui.main.EditFragment
import com.example.line.ui.main.MainFragment
import com.example.line.ui.main.MainViewModel
import kotlinx.android.synthetic.main.edit_fragment.*

class MainActivity : AppCompatActivity() {
    var image_uri: Uri? = null
    val mainViewModel=MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            mainViewModel.fragmentMode.observe(this, Observer {
                when(it){
                    1->supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainFragment(mainViewModel))
                        .commitNow()
                    2->supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EditFragment(mainViewModel))
                        .commitNow()
                }
            })
            mainViewModel.toastMsg.observe(this, Observer { Toast.makeText(this,it,it.length).show() })
            mainViewModel.inputBtnMode.observe(this, Observer {
                when(it){
                    1->{
                        //camera intent
                        if(ContextCompat.checkSelfPermission(this@MainActivity,android.Manifest.permission.CAMERA)
                            ==PackageManager.PERMISSION_GRANTED)
                            {
                                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    ==PackageManager.PERMISSION_GRANTED ) {
                                    val values = ContentValues()
                                    values.put(MediaStore.Images.Media.TITLE, "New Picture")
                                    values.put(
                                        MediaStore.Images.Media.DESCRIPTION,
                                        "From the Camera"
                                    )
                                    image_uri = contentResolver.insert(
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        values
                                    )
                                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
                                    startActivityForResult(cameraIntent, 100)
                                }
                                else{
                                    ActivityCompat.requestPermissions(this,
                                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),0)
                                }
                        }
                        else{
                            ActivityCompat.requestPermissions(this,
                                arrayOf(android.Manifest.permission.CAMERA),0)
                        }
                    }
                    2->{
                        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            ==PackageManager.PERMISSION_GRANTED ){
                            val intent = Intent(Intent.ACTION_PICK)
                            intent.type = "image/*"
                            startActivityForResult(intent,200)
                        }
                        else{
                            ActivityCompat.requestPermissions(this,
                                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),0)
                        }
                    }

                }
            })

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) return
        if (requestCode == 100) mainViewModel.imageAdd(image_uri as Any)
        else if(requestCode == 200) mainViewModel.imageAdd(data?.data as Any)

    }
}
