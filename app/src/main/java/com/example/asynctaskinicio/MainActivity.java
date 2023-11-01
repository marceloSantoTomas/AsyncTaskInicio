package com.example.asynctaskinicio;

import androidx.appcompat.app.AppCompatActivity; // Importa la clase AppCompatActivity del paquete androidx

import android.content.Intent; // Importa la clase Intent del paquete android
import android.os.AsyncTask; // Importa la clase AsyncTask del paquete android
import android.os.Bundle; // Importa la clase Bundle del paquete android
import android.view.View; // Importa la clase View del paquete android
import android.widget.Button; // Importa la clase Button del paquete android
import android.widget.EditText; // Importa la clase EditText del paquete android
import android.widget.ProgressBar; // Importa la clase ProgressBar del paquete android

public class MainActivity extends AppCompatActivity { // Define una clase llamada MainActivity que hereda de AppCompatActivity

    private Button Iniciar; // Declaración de la variable  Iniciar de tipo Button
    private EditText Usuario, Password; // Declaración de dos variables miembro llamadas Usuario y Password
    private ProgressBar pb; // Declaración de la variable  llamada pb de tipo ProgressBar

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Método que se ejecuta cuando la actividad se crea
        super.onCreate(savedInstanceState); // Llama al método onCreate de la clase base (AppCompatActivity)
        setContentView(R.layout.activity_main); // Establece el diseño de la actividad a activity_main.xml

        Iniciar = findViewById(R.id.btnIniciarSesion); // Asigna el botón con el ID btnIniciarSesion a la variable Iniciar
        Usuario = findViewById(R.id.txtUsuario); // Asigna el campo de texto con el ID txtUsuario a la variable Usuario
        Password = findViewById(R.id.txtContrasenia); // Asigna el campo de texto con el ID txtContrasenia a la variable Password
        pb = findViewById(R.id.progressBar); // Asigna la barra de progreso con el ID progressBar a la variable pb

        Iniciar.setOnClickListener(new View.OnClickListener() { // Configura un listener para el botón Iniciar
            @Override
            public void onClick(View view) { // Método que se ejecuta cuando se hace clic en el botón Iniciar
                new Task().execute(Usuario.getText().toString()); // Crea una nueva instancia de la clase Task y ejecuta la tarea en segundo plano con el usuario ingresado
            }
        });
    }

    class Task extends AsyncTask<String, Void, String> { // Define una clase interna llamada Task que extiende AsyncTask
        @Override
        protected void onPreExecute() { // Método que se ejecuta antes de iniciar la tarea en segundo plano
            pb.setVisibility(View.VISIBLE); // Hace visible la barra de progreso
            Iniciar.setEnabled(false); // Deshabilita el botón Iniciar
        }

        @Override
        protected String doInBackground(String... strings) { // Método que realiza la tarea en segundo plano
            try {
                Thread.sleep(5000); // Pausa la ejecución durante 5 segundos (simulando una tarea larga)
            } catch (InterruptedException e) { // Captura cualquier excepción que ocurra durante la pausa
                e.printStackTrace(); // Imprime la excepción por pantalla
            }
            return strings[0]; // Retorna el usuario que se pasó como argumento
        }

        @Override
        protected void onPostExecute(String s) { // Método que se ejecuta después de completar la tarea en segundo plano
            pb.setVisibility(View.INVISIBLE); // Oculta la barra de progreso
            Iniciar.setEnabled(true); // Habilita nuevamente el botón Iniciar
            Intent intent = new Intent(MainActivity.this, Resultado.class); // Crea un Intent para pasar a la actividad Resultado
            intent.putExtra("Usuario", Usuario.getText().toString()); // Agrega un dato extra (usuario) al Intent
            startActivity(intent); // Inicia la actividad Resultado
        }
    }
}