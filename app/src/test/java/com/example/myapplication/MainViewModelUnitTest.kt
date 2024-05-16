package com.example.myapplication //Определяет пакет для класса `MainViewModelUnitTest`.
// Необходимые импорты библиотеки `junit` для тестирования и библиотеки `apache.commons.math3` для статистических вычислений.

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
// Класс `MainViewModelUnitTest` является классом JUnit для тестирования класса `MainViewModel`.
class MainViewModelUnitTest {

    private val random = Random() //Поле для создания случайных чисел.
    private lateinit var viewModel: MainViewModel //Поле для экземпляра тестируемого класса `MainViewModel`.
    private var generatedNums = ArrayList<Double>(0) //Поле для хранения сгенерированных значений.

    @Before
    fun setUp() { //Выполняется перед каждым тестом, инициализирует экземпляр `MainViewModel`.
        viewModel = MainViewModel()
    }



    @Test
    //Тестирует метод `getResult()` класса `MainViewModel`.
    //Генерирует случайные значения для среднего и дисперсии.
    //Сгенерирует 1 500 000 значений с использованием метода `getResult()`.
    //Проверяет, что сгенерированные значения больше 0.
    ///Проверяет статистические свойства сгенерированных значений (среднее, дисперсию, асимметрию и эксцесс).
//`testTextView()`:** Тестирует текст в представлении.
    //Генерирует случайные значения для среднего и дисперсии.
    //Вызывает метод `getResult()`, проверяя, что возвращаемое значение не равно `null` и больше 0.
    // Проверяет, что поле `randomNumber` не равно `null` и содержит значение, равное результату.

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
//Используется для проверки статистических свойств сгенерированных значений.
// Сравнивает среднее, дисперсию, асимметрию и эксцесс сгенерированных значений с ожидаемыми значениями.
//Метод checkLog():

//Входные параметры:
    // nums: Список сгенерированных значений.
    // m: Ожидаемое среднее.
    // v: Ожидаемая дисперсия.
    //sk: Ожидаемая асимметрия.
    // kur: Ожидаемый эксцесс.
//Действия:
    // Конвертирует список nums в массив double.
    // Вычисляет среднее gm с помощью StatUtils.mean().
    // Вычисляет дисперсию gv с помощью StatUtils.variance().
    //Вычисляет асимметрию skewness с помощью DescriptiveStatistics.skewness().
    // Вычисляет эксцесс kurtosis с помощью DescriptiveStatistics.kurtosis().
    // Печатает статистические свойства сгенерированных значений.
    // Проверяет, что среднее, дисперсия, асимметрия и эксцесс сгенерированных значений близки к ожидаемым значениям с использованием TestCase.assertEquals().


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
    //Тест testTextView():

// Действия:
    ///Для каждого из 1 500 000 итераций:
        // Генерирует случайные значения для mean и variance.
        // Вызывает метод getResult() для получения результата.
        // Проверяет, что результат не равен null и больше 0.
        // Проверяет, что поле randomNumber не равно null и содержит значение, равное результату.
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
