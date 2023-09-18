package com.natanduarte.mvvmstudy

import android.content.Intent
import android.net.Uri
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

    private lateinit var link: String

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
            binding.type.text = "type: ${activity.type}"
            binding.participants.text = "participants: ${activity.participants}"
            binding.price.text = "price: ${activity.price}"
            link = activity.link!!
            if (link.isNotBlank()) {
                binding.link.isEnabled = true
                binding.link.isClickable = true
            } else {
                binding.link.isEnabled = false
                binding.link.isClickable = false
            }
            binding.key.text = "key: ${activity.key}"
            binding.accessibility.text = "accessibility: ${activity.accessibility}"
        }

        viewModel.errorMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }

    override fun onResume() {
        super.onResume()

        viewModel.getActivity()
        binding.link.setOnClickListener {
            if (link.isNotBlank()) {
                openLink(link)
            }
        }
    }
}
