package info.androidtv.mislugares;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
//Las 5 ultimos las tuve que poner a mano, no automcpletaba. Podias darle a importar clase sin que te especificara eso si.


public class VistaLugarFragment extends Fragment {
    private long id;
    private Lugar lugar;
    final static int RESULTADO_EDITAR = 1;
//    private ImageView imageView;
    final static int RESULTADO_GALERIA= 2;
    final static int RESULTADO_FOTO= 3;
    private Uri uriFoto;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.vista_lugar);
//        Bundle extras = getIntent().getExtras();
//        id = extras.getLong("id", -1);
//        imageView = (ImageView) findViewById(R.id.foto);
//        actualizarVistas();
//    }

    private View v;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor,
                             Bundle savedInstanceState) {
        View vista = inflador.inflate(R.layout.vista_lugar,contenedor,false);
        setHasOptionsMenu(true);
        return vista;
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        v = getView();
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            id = extras.getLong("id", -1);
            if (id != -1) {
//                actualizarVistas(id); todo
            }
        }
    }

    public void lanzarVistaLugar(View view){
        Intent i = new Intent(getActivity(), VistaLugarFragment.class);
        i.putExtra("id", (long)0);
        startActivity(i);
    }

    //Para el menu vista_lugar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.vista_lugar, menu);
//        return true;
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.vista_lugar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_compartir:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,
                        lugar.getNombre() + " - " + lugar.getUrl());
                startActivity(intent);
                return true;
            case R.id.accion_llegar:
                verMapa(null);
                return true;
            case R.id.accion_editar:
//                EdicionLugarActivity((int) id); //todo como le paso el id?
                // Esto funcionara?, copiado de  MainActivity para lanzar esta clase pero modificado para lanzar la otra.
//                Intent i = new Intent(VistaLugarFragment.this,
//                        EdicionLugarActivity.class);

//                i.putExtra("id", id);
//                startActivityForResult(i, RESULTADO_EDITAR);
                return true;
            case R.id.accion_borrar:
//                MainActivity.lugares.borrar((int) id);
//                finish();
//                return true;
                borrarLugar((int) id);//probando, llama a metodo inferior.
                return true;
//            case R.id.accion_cancelar:
//                if (_id!=-1) {
//                    MainActivity.lugares.borrar((int) _id);
//                }
//                finish();
//            getActivity().finish();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Comprobacion de borrado
    public void borrarLugar(final int id) {
        //Todo Insertado aqui cuadro de dialogo para preguntar si hacerlo o no
        //insertado copiando el de VistaLugarActivity

        new AlertDialog.Builder(getActivity())
                .setTitle("Borrado de lugar")
                .setMessage("¿Estás seguro que quieres eliminar este lugar?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        MainActivity.lugares.borrar((int) id);
                        SelectorFragment.adaptador.setCursor(
                                MainActivity.lugares.extraeCursor());
                        SelectorFragment.adaptador.notifyDataSetChanged();
//                        getActivity().finish();
                        SelectorFragment selectorFragment = (SelectorFragment) getActivity().
                                getSupportFragmentManager().findFragmentById(R.id.selector_fragment);
                        if (selectorFragment == null) {
                            getActivity().finish();
                        } else {
                            ((MainActivity) getActivity()).muestraLugar(0);
                        }
                    }})
                .setNegativeButton("Cancelar", null)
                .show();
    }

    public void actualizarVistas() {
//        lugar = MainActivity.lugares.elemento((int) id);
//        lugar= SelectorFragment.adaptador.lugarPosicion((int) id);
        this.id = id;
        lugar = SelectorFragment.adaptador.lugarPosicion((int) id);
        if (lugar != null) {

            TextView nombre = (TextView) v.findViewById(R.id.nombre);
            nombre.setText(lugar.getNombre());
            ImageView logo_tipo = (ImageView) v.findViewById(R.id.logo_tipo);
            logo_tipo.setImageResource(lugar.getTipo().getRecurso());
            TextView tipo = (TextView) v.findViewById(R.id.tipo);
            tipo.setText(lugar.getTipo().getTexto());
            ponerFoto((ImageView)v.findViewById(R.id.foto), lugar.getFoto());
            actualizaLugar();
        }
//
//        TextView direccion = (TextView) findViewById(R.id.direccion);
//        direccion.setText(lugar.getDireccion());
        if (lugar.getDireccion().isEmpty()) {
            v.findViewById(R.id.direccion).setVisibility(View.GONE);
        } else {
            v.findViewById(R.id.direccion).setVisibility(View.VISIBLE);
            TextView direccion = (TextView) v.findViewById(R.id.direccion);
            direccion.setText(lugar.getDireccion());
        }
//        TextView telefono = (TextView) findViewById(R.id.telefono);
//        telefono.setText(Integer.toString(lugar.getTelefono()));
        //Ocultar si no existe el campo
        if (lugar.getTelefono() == 0) {
            v.findViewById(R.id.telefono).setVisibility(View.GONE);
        } else {
            v.findViewById(R.id.telefono).setVisibility(View.VISIBLE);
            TextView telefono = (TextView) v.findViewById(R.id.telefono);
            telefono.setText(Integer.toString(lugar.getTelefono()));
        }
//        TextView url = (TextView) findViewById(R.id.url);
//        url.setText(lugar.getUrl());
        //Ocultar si no existe el campo
        if (lugar.getUrl().isEmpty()) {
            v.findViewById(R.id.url).setVisibility(View.GONE);
        } else {
            v.findViewById(R.id.url).setVisibility(View.VISIBLE);
            TextView url = (TextView) v.findViewById(R.id.url);
            url.setText(lugar.getUrl());
        }
//        TextView comentario = (TextView) findViewById(R.id.comentario);
//        comentario.setText(lugar.getComentario());
        if (lugar.getComentario().isEmpty()) {
            v.findViewById(R.id.comentario).setVisibility(View.GONE);
        } else {
            v.findViewById(R.id.comentario).setVisibility(View.VISIBLE);
            TextView comentario = (TextView) v.findViewById(R.id.comentario);
            comentario.setText(lugar.getComentario());
        }
        TextView fecha = (TextView) v.findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = (TextView) v.findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = (RatingBar) v.findViewById(R.id.valoracion);
        valoracion.setOnRatingBarChangeListener(null);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,
                                                float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                        actualizaLugar();

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        if (requestCode == RESULTADO_EDITAR) {
            getActivity().finish();
//            actualizarVistas(id); todo ni idea pero quiero terminar
            v.findViewById(R.id.scrollView1).invalidate();
        } else if (requestCode == RESULTADO_GALERIA) {
            if (resultCode == Activity.RESULT_OK) {
                lugar.setFoto(data.getDataString());
                ponerFoto((ImageView)v.findViewById(R.id.foto), lugar.getFoto());
            } else {
                Toast.makeText(getActivity(), "Foto no cargada", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == RESULTADO_FOTO) {
            if (resultCode == Activity.RESULT_OK
                    && lugar!=null && uriFoto!=null) {
                lugar.setFoto(uriFoto.toString());
                ponerFoto((ImageView)v.findViewById(R.id.foto), lugar.getFoto());
            } else {
                Toast.makeText(getActivity(), "Error en captura", Toast.LENGTH_LONG).show();
            }
        }
    }

    //
    public void verMapa(View view) {
        Uri uri;
        double lat = lugar.getPosicion().getLatitud();
        double lon = lugar.getPosicion().getLongitud();
        if (lat != 0 || lon != 0) {
            uri = Uri.parse("geo:" + lat + "," + lon);
        } else {
            uri = Uri.parse("geo:0,0?q=" + lugar.getDireccion());
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void llamadaTelefono(View view) {
        startActivity(new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + lugar.getTelefono())));
    }
    public void pgWeb(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(lugar.getUrl())));
    }

    public void galeria(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULTADO_GALERIA);
    }

    protected void ponerFoto(ImageView imageView, String uri) {
//            if (uri != null&& !uri.isEmpty() && !uri.equals("null")) {
//                (ImageView)v.findViewById(R.id.foto).setImageBitmap(reduceBitmap(getActivity(), uri, 1024,   1024));
//            } else{
//                (ImageView)v.findViewById(R.id.foto).setImageBitmap(null);
//            }
    }
//import java.io.File; tuve que ponerlo al principio porque "File" no autoresolvia lo que necesitaba.
    public void tomarFoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        uriFoto = Uri.fromFile(new File(
                Environment.getExternalStorageDirectory() + File.separator
                        + "img_" + (System.currentTimeMillis() / 1000) + ".jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);
        startActivityForResult(intent, RESULTADO_FOTO);
    }

    public void eliminarFoto(View view) {
        lugar.setFoto(null);
        ponerFoto((ImageView)v.findViewById(R.id.foto), null);
        actualizaLugar();
    }

    public static Bitmap reduceBitmap(Context contexto, String uri,
                                      int maxAncho, int maxAlto) {
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(contexto.getContentResolver()
                    .openInputStream(Uri.parse(uri)), null, options);
            options.inSampleSize = (int) Math.max(
                    Math.ceil(options.outWidth / maxAncho),
                    Math.ceil(options.outHeight / maxAlto));
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(contexto.getContentResolver()
                    .openInputStream(Uri.parse(uri)), null, options);
        } catch (FileNotFoundException e) {
            Toast.makeText(contexto, "Fichero/recurso no encontrado",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return null;
        }
    }

    public static void solicitarPermiso(final String permiso, String
            justificacion, final int requestCode, final Activity actividad) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad,
                permiso)){
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, requestCode);
                        }})
                    .show();
        } else {
            ActivityCompat.requestPermissions(actividad,
                    new String[]{permiso}, requestCode);
        }
    }

    void actualizaLugar(){
        int _id = SelectorFragment.adaptador.idPosicion((int) id);
        MainActivity.lugares.actualiza(_id, lugar);
        SelectorFragment.adaptador.setCursor(MainActivity.lugares.extraeCursor());
        SelectorFragment.adaptador.notifyItemChanged((int) id);
    }

    //metido porque no se donde meter esta linea modificada en fragment
    public void lanzarEdicionLugar(final long id) {
        Intent i = new Intent(getActivity(), EdicionLugarActivity.class);
        i.putExtra("id", id);
        startActivityForResult(i, RESULTADO_EDITAR);
    }

}