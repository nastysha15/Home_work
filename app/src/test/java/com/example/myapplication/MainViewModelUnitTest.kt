package com.example.myapplication

import junit.framework.TestCase
import org.apache.commons.math3.stat.StatUtils
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.sqrt

class MainViewModelUnitTest {

    private val random = Random()
    private lateinit var viewModel: MainViewModel
    private var generatedNums = ArrayList<Double>(0)

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }


    @Test
    fun formulaCalculation() {
        val mean = random.nextDouble()
        val variance = random.nextDouble()
        println("Mean and variance: $mean $variance")

        viewModel.mean = mean
        viewModel.variance = variance

        for (i in 0..1500000){
            val res = viewModel.getResult()
            assertEquals("result must be >= 0, but $res",
                true, res != null && res > 0)
            generatedNums.add(res!!)
        }

        checkLog(
            generatedNums,
            exp(mean + variance / 2.0),
            exp(2 * mean + variance) * (exp(variance) - 1),
            sqrt(exp(variance) - 1) * (exp(variance) + 2),
            exp(4 * variance) + 2 * exp(3 * variance) + 3 * exp(2 * variance) - 6
        )
    }

    private fun checkLog(nums: ArrayList<Double>, m: Double, v: Double, sk: Double, kur: Double) {
        val dNums = nums.toDoubleArray()
        val gm = StatUtils.mean(dNums)
        val gv = StatUtils.variance(dNums)
        val skewness = DescriptiveStatistics(dNums).skewness
        val kurtosis = DescriptiveStatistics(dNums).kurtosis
        println(
            "Test " +
                    "${abs(gm - m)} ${abs(gv - v)} " +
                    "${abs(skewness - sk)} ${abs(kurtosis - kur)}"
        )

        TestCase.assertEquals("Mean is different", m, gm, 1E-1)
        TestCase.assertEquals("Variance is different", v, gv, 0.8)
        TestCase.assertEquals("Skewness is different", sk, skewness, 1.1)
        TestCase.assertEquals("Kurtosis is different", kur, kurtosis, 3.5)
    }

    @Test
    fun testTextView() {
        for (i in 0..1500000) {
            viewModel.mean = random.nextDouble()
            viewModel.variance = random.nextDouble()

            val result = viewModel.getResult()

            assertEquals("Result must be not null",true,
                result != null)
            assertEquals("Result must be more then 0",true,
                result!! >= 0)
            assertEquals("Filed \"randomNumber\" must be not null",true,
                viewModel.randomNumber != null)
            assertEquals("Filed \"randomNumber\" must be equal result", true,
                viewModel.randomNumber!! == result)
        }

    }

}
