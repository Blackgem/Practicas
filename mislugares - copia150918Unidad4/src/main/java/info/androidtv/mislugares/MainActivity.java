package info.androidtv.mislugares;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static Lugares lugares = new LugaresVector();//añadido en temario.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout/*.activity_main*/.edicion_lugar);
//        setContentView(R.layout.edicion_lugar);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.content_main);

        Button bLugares = (Button) findViewById(R.id.button01);
//        bLugares.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                mostrarPreferencias();
//            }
//        });

        //Configurando primer boton para lo que suponemos que deberia salir.
        bLugares.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                lanzarVistaLugar(null);
            }
        });

        Button bAcercaDe = (Button) findViewById(R.id.button03);
        bAcercaDe.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                lanzarAcercaDe(null);
            }
        });

        Button bPreferencias = (Button) findViewById(R.id.button02);
        bPreferencias.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                lanzarPreferencias(null);
            }
        });

        Button bSalir =(Button) findViewById(R.id.button04);
        bSalir.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void lanzarAcercaDe(View view){
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }

    public void lanzarPreferencias(View view){
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivity(i);
    }

    public void salir(View view){
        finish();
    }


//    Para ejecutar el menu
@Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true; /** true -> el menú ya está visible */
}
//Al seleccionar el menu
    @Override public boolean onOptionsItemSelected(MenuItem item) { int id = item.getItemId();
        if (id == R.id.action_settings) {
            lanzarPreferencias(null);
            return true;
        }
        if (id == R.id. acercaDe) {
            lanzarAcercaDe(null);
            return true;
        }
        if(id == R.id.menu_buscar) {
            lanzarVistaLugar(null);
            return true;
        }//Añadido tras buscar
        return super.onOptionsItemSelected(item);

    }
    //Mostrar toast con parametros guardados.
    public void mostrarPreferencias(){
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String s = "notificaciones: "+ pref.getBoolean("notificaciones",true)
                +", máximo a listar: " + pref.getString("maximo","?");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

//    public void lanzarVistaLugar(View view){
//        Intent i = new Intent(this, VistaLugarActivity.class);
//        i.putExtra("id", (long)0);
//        startActivity(i);
//    }
    public void lanzarVistaLugar(View view){
        final EditText entrada = new EditText(this);
        entrada.setText("0");
        new AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("indica su id:")
                .setView(entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        long id = Long.parseLong(entrada.getText().toString());
                        Intent i = new Intent(MainActivity.this,
                                VistaLugarActivity.class);
                        i.putExtra("id", id);
                        startActivity(i);
                    }})
                .setNegativeButton("Cancelar", null)
                .show();
    }

}
