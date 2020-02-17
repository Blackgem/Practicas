package info.androidtv.mislugares;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {
    //    Comentadas pues se usara ahora base de datos
    //    public static Lugares lugares = new LugaresVector();//añadido en temario.
    //    public AdaptadorLugares adaptador;
    public static LugaresBD lugares;
    public static AdaptadorLugaresBD adaptador;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LocationManager manejador;
    private Location mejorLocaliz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lugares = new LugaresBD(this);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout/*.activity_main*/.edicion_lugar);
//        setContentView(R.layout.edicion_lugar);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.content_main);

//        Button bLugares = (Button) findViewById(R.id.button01);
//        bLugares.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                mostrarPreferencias();
//            }
//        });

        //Configurando primer boton para lo que suponemos que deberia salir.
//        bLugares.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                lanzarVistaLugar(null);
//            }
//        });
//
//        Button bAcercaDe = (Button) findViewById(R.id.button03);
//        bAcercaDe.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                lanzarAcercaDe(null);
//            }
//        });
//
//        Button bPreferencias = (Button) findViewById(R.id.button02);
//        bPreferencias.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                lanzarPreferencias(null);
//            }
//        });
//
//        Button bSalir =(Button) findViewById(R.id.button04);
//        bSalir.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                finish();
//            }
//        });

        //Añadido para recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        adaptador = new AdaptadorLugares(this, lugares);
        adaptador = new AdaptadorLugaresBD(this, lugares, lugares.extraeCursor());
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Incluido para recylerview
        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VistaLugarActivity.class);
                i.putExtra("id", (long) recyclerView.getChildAdapterPosition(v));
                startActivity(i);
            }
        });

        //Para test
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
        ultimaLocalizazion();
    }

    public void lanzarAcercaDe(View view){
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }

//
    static final int RESULTADO_PREFERENCIAS = 0;
    public void lanzarPreferencias(View view) {
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivityForResult(i, RESULTADO_PREFERENCIAS);
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
        if (id==R.id.menu_mapa) {
            Intent intent = new Intent(this, MapaActivity.class);
            startActivity(intent);
        }
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
    @Override protected void onResume() {
        super.onResume();
        activarProveedores();
    }
    private void activarProveedores() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.
                ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                manejador.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        20 * 1000, 5, this);
            }
            if (manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                manejador.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        10 * 1000, 10, this);
            }
        } else {
//            solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
//                    "Sin el permiso localización no puedo mostrar la distancia"+
//                            " a los lugares.", SOLICITUD_PERMISO_LOCALIZACION, this);
        }
    }
    @Override protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.
                ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            manejador.removeUpdates(this);
        }
    }

    void ultimaLocalizazion(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.
                ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (manejador.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                actualizaMejorLocaliz(manejador.getLastKnownLocation(
                        LocationManager.GPS_PROVIDER));
            }
            if (manejador.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                actualizaMejorLocaliz(manejador.getLastKnownLocation(
                        LocationManager.NETWORK_PROVIDER));
            } else  {
//                solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
//                        "Sin el permiso localización no puedo mostrar la distancia"+
//                                " a los lugares.", SOLICITUD_PERMISO_LOCALIZACION, this);
            }
//            @Override
//            public void onRequestPermissionsResult(int requestCode,
//            String[] permissions, int[] grantResults) {
//                if (requestCode == SOLICITUD_PERMISO_LOCALIZACION) {
//                    if (grantResults.length== 1 &&
//                            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                        ultimaLocalizazion();
//                        activarProveedores();
//                        adaptador.notifyDataSetChanged();
//                    }
//                }
//            }
        }
    }

    @Override public void onLocationChanged(Location location) {
        Log.d(Lugares.TAG, "Nueva localización: "+location);
        actualizaMejorLocaliz(location);
        adaptador.notifyDataSetChanged();
    }
    @Override public void onProviderDisabled(String proveedor) {
        Log.d(Lugares.TAG, "Se deshabilita: "+proveedor);
        activarProveedores();
    }
    @Override   public void onProviderEnabled(String proveedor) {
        Log.d(Lugares.TAG, "Se habilita: "+proveedor);
        activarProveedores();
    }
    @Override
    public void onStatusChanged(String proveedor, int estado, Bundle extras) {
        Log.d(Lugares.TAG, "Cambia estado: "+proveedor);
        activarProveedores();
    }

    private static final long DOS_MINUTOS = 2 * 60 * 1000;

    private void actualizaMejorLocaliz(Location localiz) {
        if (localiz != null && (mejorLocaliz == null
                || localiz.getAccuracy() < 2*mejorLocaliz.getAccuracy()
                || localiz.getTime() - mejorLocaliz.getTime() > DOS_MINUTOS)) {
            Log.d(Lugares.TAG, "Nueva mejor localización");
            mejorLocaliz = localiz;
            Lugares.posicionActual.setLatitud(localiz.getLatitude());
            Lugares.posicionActual.setLongitud(localiz.getLongitude());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == RESULTADO_PREFERENCIAS) {
            adaptador.setCursor(MainActivity.lugares.extraeCursor());
            adaptador.notifyDataSetChanged();
        }
    }

//    fab.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//            long _id = lugares.nuevo();
//            Intent i = new Intent(MainActivity.this, EdicionLugarActivity.class);
//            i.putExtra("_id", _id);
//            startActivity(i);
//        }
//    });
}
