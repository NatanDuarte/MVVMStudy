package com.natanduarte.mvvmstudy

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.natanduarte.mvvmstudy.databinding.ActivityMainBinding
import com.natanduarte.mvvmstudy.repository.MainRepository
import com.natanduarte.mvvmstudy.rest.RetrofitService
import com.natanduarte.mvvmstudy.viewmodel.main.MainViewModel
import com.natanduarte.mvvmstudy.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this, MainViewModelFactory(MainRepository(retrofitService))
        ).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        viewModel.activity.observe(this) { activity ->
            Log.i("DEV", "OnStart: $activity")

            binding.activity.text = activity.activity
            binding.type.text = activity.type
            binding.participants.text = activity.participants.toString()
            binding.price.text = activity.price.toString()
            binding.link.text = activity.link
            binding.key.text = activity.key
            binding.accessibility.text = activity.accessibility.toString()
        }

        viewModel.errorMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getActivity()
    }
}
