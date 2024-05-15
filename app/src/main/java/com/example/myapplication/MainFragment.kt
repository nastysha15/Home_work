package com.example.myapplication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var meanValue: EditText
    private lateinit var varianceValue: EditText
    private lateinit var randomNum: TextView
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        meanValue = view.findViewById(R.id.mean_val)
        varianceValue = view.findViewById(R.id.variance_value)
        randomNum = view.findViewById(R.id.random_number_result)
        btn = view.findViewById(R.id.get_random_num)

        randomNum.setText(viewModel.randomNumber?.toString()?: getString(R.string.nul))

        btn.setOnClickListener {
            viewModel.mean = meanValue.text.toString().toDoubleOrNull()

            viewModel.variance = varianceValue.text.toString().toDoubleOrNull()

            if (viewModel.getResult() != null) {
                randomNum.setText(viewModel.randomNumber.toString())
            }
        }
    }
}
