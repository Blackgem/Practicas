package info.androidtv.mislugares;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class EdicionLugarActivity extends AppCompatActivity {

    private long id;
    private Lugar lugar;
    private EditText nombre;
    private Spinner tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edicion_lugar);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        lugar = MainActivity.lugares.elemento((int) id);
//        TextView nombre = (TextView) findViewById(R.id.nombre);
//        nombre.setText(lugar.getNombre());
//
//        findViewById(R.id.direccion).setVisibility(View.VISIBLE);
//        TextView direccion = (TextView) findViewById(R.id.direccion);
//        direccion.setText(lugar.getDireccion());
//
//        findViewById(R.id.telefono).setVisibility(View.VISIBLE);
//        TextView telefono = (TextView) findViewById(R.id.telefono);
//        telefono.setText(Integer.toString(lugar.getTelefono()));
//
//        findViewById(R.id.url).setVisibility(View.VISIBLE);
//        TextView url = (TextView) findViewById(R.id.url);
//        url.setText(lugar.getUrl());
//
//        findViewById(R.id.comentario).setVisibility(View.VISIBLE);
//        TextView comentario = (TextView) findViewById(R.id.comentario);
//        comentario.setText(lugar.getComentario());

        //Copiado de vista y quitado innecesarios cambiado textview por edittext
        //Al venir declarado al inicio de la clase no hace especificar el tipo de datos que usa.
        nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());

        findViewById(R.id.direccion).setVisibility(View.VISIBLE);
        direccion = findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());

        findViewById(R.id.telefono).setVisibility(View.VISIBLE);
        telefono = findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));

        findViewById(R.id.url).setVisibility(View.VISIBLE);
        url = findViewById(R.id.url);
        url.setText(lugar.getUrl());

        findViewById(R.id.comentario).setVisibility(View.VISIBLE);
        comentario = findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());

        tipo = findViewById(R.id.tipo);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, TipoLugar.getNombres());
        adaptador.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        tipo.setAdapter(adaptador);
        tipo.setSelection(lugar.getTipo().ordinal());
//    El paso de parámetros para obtener id y lugar puede realizarse de la misma forma.
    }

//todo Siguientes 2 metodos inventados sobre la marcha.
    //    Para ejecutar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.guardar, menu);
        return true; /** true -> el menú ya está visible */
    }
    //Al seleccionar el menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.cancelar:
                finish();
                return true;

            case R.id.guardar:
                lugar.setNombre(nombre.getText().toString());
                lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
                lugar.setDireccion(direccion.getText().toString());
                lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
                lugar.setUrl(url.getText().toString());
                lugar.setComentario(comentario.getText().toString());
                MainActivity.lugares.actualiza((int) id, (Lugar) lugar);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}