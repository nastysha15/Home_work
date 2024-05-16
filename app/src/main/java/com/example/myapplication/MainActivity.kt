package com.example.myapplication //Определяет пакет для класса MainActivity.
// Необходимые импорты для функциональности Activity.


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() { // Определяет класс MainActivity как подкласс AppCompatActivity, что делает его компонентом пользовательского интерфейса.
// Входной параметр: savedInstanceState: Объект Bundle, содержащий сохраненное состояние Activity.
// Действия:
    // Вызывает метод super.onCreate() для инициализации Activity.
    // Устанавливает макет activity_main.xml в качестве содержимого Activity.
    //Проверяет, был ли ранее сохранен экземпляр Activity (savedInstanceState не равен null). Если нет, то:
        //Создает новый фрагмент MainFragment с помощью MainFragment().
        // Добавляет фрагмент в контейнер R.id.container с помощью supportFragmentManager.beginTransaction().
        // Выполняет транзакцию немедленно с помощью commitNow().
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commitNow()
        }
    }
}
//
