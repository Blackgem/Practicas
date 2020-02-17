package info.androidtv.x_comunicacionactividades;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class condiciones extends Activity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.condiciones);
//recogemos los datos pasados por put.extra
        Bundle extras = getIntent().getExtras();
        String s = extras.getString("nombre");
        TextView salida = (TextView) findViewById(R.id.textView);
        salida.setText("Hola " + s + ", ¿Aceptas las condiciones?");

        Button bAceptar = (Button) findViewById(R.id.botonaceptar);
        bAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                aceptar(null);
            }
        });
        Button bRechazar = (Button) findViewById(R.id.botonrechazar);
        bRechazar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                rechazar(null);
            }
        });

    }
    public void aceptar(View view){
        Intent intent = new Intent();
        intent.putExtra("resultado","Aceptado");
        setResult(RESULT_OK, intent);
        finish();
    }

    //Podria ponerse esta opcion por defecto por si el usuario pulsa "Atras"
    //Asi se consideraria que ha "cancelado" si sale de la actividad.
    public void rechazar(View view){
        Intent intent = new Intent();
        intent.putExtra("resultado","Rechazado");
        setResult(RESULT_OK, intent);
        finish();
    }








}


