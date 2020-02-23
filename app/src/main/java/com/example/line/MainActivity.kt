package com.example.line

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.line.DataClass.Memo
import com.example.line.Utils.MemoRepo
import com.example.line.ui.main.DetailFragment
import com.example.line.ui.main.EditFragment
import com.example.line.ui.main.MainFragment
import com.example.line.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    var image_uri: Uri? = null
    lateinit var mainViewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val repo=MemoRepo(application)
        mainViewModel=MainViewModel(repo)
        repo.getAllMemo().observe(this, Observer {
            mainViewModel.mList.postValue(it as? ArrayList<Memo>) })
        if (savedInstanceState == null) {
            mainViewModel.fragmentMode.observe(this, Observer {
                val transaction=supportFragmentManager.beginTransaction()

                when(it){
                    1->{
                        transaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_to_right)
                        transaction
                            .replace(R.id.container, MainFragment(mainViewModel))
                            .commitNow()
                    }
                    2->{
                        transaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_to_left)
                        transaction
                            .replace(R.id.container, EditFragment(mainViewModel))
                            .commitNow()
                    }
                    3->{
                        transaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_to_left)
                        transaction
                            .replace(R.id.container, DetailFragment(mainViewModel))
                            .commitNow()
                    }
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
        else if(requestCode == 200) {
            mainViewModel.imageAdd(data?.data as Any)
        }

    }

}
