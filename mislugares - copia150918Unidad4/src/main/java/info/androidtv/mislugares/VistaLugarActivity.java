package info.androidtv.mislugares;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class VistaLugarActivity extends AppCompatActivity {
    private long id;
    private Lugar lugar;
    final static int RESULTADO_EDITAR = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        actualizarVistas();
    }

    public void lanzarVistaLugar(View view){
        Intent i = new Intent(this, VistaLugarActivity.class);
        i.putExtra("id", (long)0);
        startActivity(i);
    }

    //Para el menu vista_lugar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vista_lugar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_compartir:
                return true;
            case R.id.accion_llegar:
                return true;
            case R.id.accion_editar:
//                EdicionLugarActivity((int) id); //todo como le paso el id?
                // Esto funcionara?, copiado de  MainActivity para lanzar esta clase pero modificado para lanzar la otra.
                Intent i = new Intent(VistaLugarActivity.this,
                        EdicionLugarActivity.class);
                i.putExtra("id", id);
                startActivityForResult(i, RESULTADO_EDITAR);
                return true;
            case R.id.accion_borrar:
//                MainActivity.lugares.borrar((int) id);
//                finish();
//                return true;
                borrarLugar((int) id);//probando, llama a metodo inferior.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Comprobacion de borrado
    public void borrarLugar(final int id) {
        //Insertado aqui cuadro de dialogo para preguntar si hacerlo o no
        //insertado copiando el de VistaLugarActivity

        new AlertDialog.Builder(this)
                .setTitle("Borrado de lugar")
                .setMessage("¿Estás seguro que quieres eliminar este lugar?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        MainActivity.lugares.borrar((int) id);
//                        finish();
                    }})
                .setNegativeButton("Cancelar", null)
                .show();
    }

    public void actualizarVistas() {
        lugar = MainActivity.lugares.elemento((int) id);
        TextView nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView tipo = (TextView) findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());


//
//        TextView direccion = (TextView) findViewById(R.id.direccion);
//        direccion.setText(lugar.getDireccion());
        if (lugar.getDireccion().isEmpty()) {
            findViewById(R.id.direccion).setVisibility(View.GONE);
        } else {
            findViewById(R.id.direccion).setVisibility(View.VISIBLE);
            TextView direccion = (TextView) findViewById(R.id.direccion);
            direccion.setText(lugar.getDireccion());
        }
//        TextView telefono = (TextView) findViewById(R.id.telefono);
//        telefono.setText(Integer.toString(lugar.getTelefono()));
        //Ocultar si no existe el campo
        if (lugar.getTelefono() == 0) {
            findViewById(R.id.telefono).setVisibility(View.GONE);
        } else {
            findViewById(R.id.telefono).setVisibility(View.VISIBLE);
            TextView telefono = (TextView) findViewById(R.id.telefono);
            telefono.setText(Integer.toString(lugar.getTelefono()));
        }
//        TextView url = (TextView) findViewById(R.id.url);
//        url.setText(lugar.getUrl());
        //Ocultar si no existe el campo
        if (lugar.getUrl().isEmpty()) {
            findViewById(R.id.url).setVisibility(View.GONE);
        } else {
            findViewById(R.id.url).setVisibility(View.VISIBLE);
            TextView url = (TextView) findViewById(R.id.url);
            url.setText(lugar.getUrl());
        }
//        TextView comentario = (TextView) findViewById(R.id.comentario);
//        comentario.setText(lugar.getComentario());
        if (lugar.getComentario().isEmpty()) {
            findViewById(R.id.comentario).setVisibility(View.GONE);
        } else {
            findViewById(R.id.comentario).setVisibility(View.VISIBLE);
            TextView comentario = (TextView) findViewById(R.id.comentario);
            comentario.setText(lugar.getComentario());
        }
        TextView fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = (TextView) findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = (RatingBar) findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,
                                                float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        if (requestCode == RESULTADO_EDITAR) {
            actualizarVistas();
            findViewById(R.id.scrollView1).invalidate();
        }
    }

}