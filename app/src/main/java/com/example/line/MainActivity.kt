package com.example.line

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.line.ui.main.EditFragment
import com.example.line.ui.main.MainFragment
import com.example.line.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

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
        }
    }

}
