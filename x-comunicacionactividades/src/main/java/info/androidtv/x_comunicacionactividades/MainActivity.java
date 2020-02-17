package info.androidtv.x_comunicacionactividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bVerificar = (Button) findViewById(R.id.botonverificar);
        bVerificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                verificar(null);
            }
            });

        //Esperar respuesta al finalizar actividad
//        Intent intent = new Intent(this, condiciones.class);
//        startActivityForResult(intent, 1234);
    }




    public void verificar(View view){
        EditText entrada = (EditText) findViewById(R.id.textoentrada);
        Intent intent = new Intent(this, condiciones.class);
        //Cogemos el valor de entrada pasandolo a String para pasarlo como parametro "nombre"
        intent.putExtra("nombre", entrada.getText().toString());
        //startActivity(intent);
        //Esperar respuesta al finalizar actividad lanzada
        startActivityForResult(intent, 1234);
    }

//Para cuando finalice la actividad que usaban otros.
    @Override protected void onActivityResult (int requestCode,
                                               int resultCode, Intent data){

        //requestCode es solicitado para verificar que es de la actividad solicitada anteriormente al parecer.
        if (requestCode==1234 && resultCode==RESULT_OK) {
            String res = data.getExtras().getString("resultado");
            TextView textoSalida = (TextView) findViewById(R.id.textosalida);
            textoSalida.setText("Resultado: "+res);
        }
    }
}
