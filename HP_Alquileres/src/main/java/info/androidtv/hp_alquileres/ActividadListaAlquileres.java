package info.androidtv.hp_alquileres;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import info.androidtv.hp_alquileres.provider.Contrato.Alquileres;

//public class ActividadListaAlquileres extends AppCompatActivity implements AdaptadorAlquileres.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {
//
//    private RecyclerView listaUI;
//    private LinearLayoutManager linearLayoutManager;
//    private AdaptadorAlquileres adaptador;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.actividad_lista_alquileres);
////        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Filtro...", Snackbar.LENGTH_LONG)
//                        .setAction("Acción", null).show();
//            }
//        });
//
//        // Preparar lista
//        listaUI = (RecyclerView) findViewById(R.id.lista);
//        listaUI.setHasFixedSize(true);
//
//        linearLayoutManager = new LinearLayoutManager(this);
//        listaUI.setLayoutManager(linearLayoutManager);
//
//        adaptador = new AdaptadorAlquileres(this, this);
//        listaUI.setAdapter(adaptador);
//
//        // Iniciar loader
//        getSupportLoaderManager().restartLoader(1, null, this);
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_actividad_lista_alquileres, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onClick(AdaptadorAlquileres.ViewHolder holder, String idAlquiler) {
//        Snackbar.make(findViewById(android.R.id.content), ":id = " + idAlquiler,
//                Snackbar.LENGTH_LONG).show();
//    }
//
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return new CursorLoader(this, Alquileres.URI_CONTENIDO, null, null, null, null);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        if (adaptador != null) {
//            adaptador.swapCursor(data);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//    }
//}

/**
 * provider en el manifiest sino no se salia nada. No muestra imagenes bien tampoco pero no me preocupa.
 * Todo lo de provider es igual al final, lo que cambia es la actividad y el adaptador
 */

public class ActividadListaAlquileres extends AppCompatActivity implements AdaptadorAlquileres.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView listaUI;
    private LinearLayoutManager linearLayoutManager;
    private AdaptadorAlquileres adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_lista_alquileres);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        todo quitado toolbar porque el estilo o algo asi tiene una incluida de por si

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Filtro...", Snackbar.LENGTH_LONG)
                        .setAction("Acción", null).show();
            }
        });

        // Preparar lista
        listaUI = (RecyclerView) findViewById(R.id.lista);
        listaUI.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        listaUI.setLayoutManager(linearLayoutManager);

        adaptador = new AdaptadorAlquileres(this, this);
        listaUI.setAdapter(adaptador);

        // Iniciar loader
        getSupportLoaderManager().restartLoader(1, null, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_lista_alquileres, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
//    public void onClick(AdaptadorAlquileres.ViewHolder holder, String idAlquiler) {
    public void onClick(AdaptadorAlquileres.ViewHolder holder, int idAlquiler) {
//        todo puesto int porque asi esta puesto en el adaptador de ejercicio que es distinto al final

            Snackbar.make(findViewById(android.R.id.content), ":id = " + idAlquiler,
                Snackbar.LENGTH_LONG).show();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Alquileres.URI_CONTENIDO, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (adaptador != null) {
            adaptador.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}