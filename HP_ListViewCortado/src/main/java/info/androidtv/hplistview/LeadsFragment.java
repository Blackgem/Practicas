package info.androidtv.hplistview;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Vista para los leads del CRM
 */
//public class LeadsFragment extends Fragment {
//
////    ListView mLeadsList;
////    ArrayAdapter<String> mLeadsAdapter;
//    //CAmbiado cuando pasa de usar array interno a usar adapter
//    private ListView mLeadsList;
//    private LeadsAdapter mLeadsAdapter;
//
//
//    public LeadsFragment() {
//        // Required empty public constructor
//    }
//
//    public static LeadsFragment newInstance(/*parámetros*/) {
//        LeadsFragment fragment = new LeadsFragment();
//        // Setup parámetros
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            // Gets parámetros
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_leads, container, false);
//
//        LeadsFragment leadsFragment = (LeadsFragment)
//                getSupportFragmentManager().findFragmentById(R.id.leads_container);
//
//        if (leadsFragment == null) {
//            leadsFragment = LeadsFragment.newInstance();
//            getSupportFragmentManager().beginTransaction()
////                    .add(R.id.leads_container, leadsFragment)
//                    .commit();
//        }
////        Cambiar leads_container por leads_list ?, no fui capaz de hacerlo funcioanr de normal.
//
//        //Relacionando la lista con el adaptador
//        mLeadsList.setAdapter(mLeadsAdapter);
//
//        // Eventos
//        mLeadsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Lead currentLead = mLeadsAdapter.getItem(position);
//                Toast.makeText(getActivity(),
//                        "Iniciar screen de detalle para: \n" + currentLead.getName(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        setHasOptionsMenu(true);
//        return root;
//    }
//
////    @Override
////    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
////        super.onCreateOptionsMenu(menu, inflater);
////        inflater.inflate(R.menu.menu_leads_list, menu);
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        int id = item.getItemId();
////        if (id == R.id.action_delete_all) {
////            // Eliminar todos los leads
////            mLeadsAdapter.clear();
////            return true;
////        }
////        return super.onOptionsItemSelected(item);
////    }
//}

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Vista para los leads del CRM
 */
public class LeadsFragment extends Fragment {

    ListView mLeadsList;
    ArrayAdapter<String> mLeadsAdapter;

    public LeadsFragment() {
        // Required empty public constructor
    }

    public static LeadsFragment newInstance(/*parámetros*/) {
        LeadsFragment fragment = new LeadsFragment();
        // Setup parámetros
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Gets parámetros
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_leads, container, false);
        // Instancia del ListView.
        mLeadsList = (ListView) root.findViewById(R.id.leads_list);
        String[] leadsNames = {
                "Alexander Pierrot",
                "Carlos Lopez",
                "Sara Bonz",
                "Liliana Clarence",
                "Benito Peralta",
                "Juan Jaramillo",
                "Christian Steps",
                "Alexa Giraldo",
                "Linda Murillo",
                "Lizeth Astrada"
        };

        String[] leadsNames2 = {
                "Carlos Lopez",
                "Carlos Lopez",
                "Carlos Lopez",
                "Carlos Lopez",
                "Alexander Pierrot",
                "Carlos Lopez",
                "Sara Bonz",
                "Liliana Clarence",
                "Benito Peralta",
                "Juan Jaramillo",
                "Christian Steps",
                "Alexa Giraldo",
                "Linda Murillo",
                "Lizeth Astrada"
        };

        mLeadsAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                leadsNames);

        mLeadsList.setAdapter(mLeadsAdapter);

        return root;
    }
}


//todo
///**
// * Vista para los leads del CRM
// */
//public class LeadsFragment extends Fragment {
//    private ListView mLeadsList;
//    private LeadsAdapter mLeadsAdapter;
//
//    public LeadsFragment() {
//        // Required empty public constructor
//    }
//
//    public static LeadsFragment newInstance(/*parámetros*/) {
//        LeadsFragment fragment = new LeadsFragment();
//        // Setup parámetros
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            // Gets parámetros
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_leads, container, false);
//
//        // Instancia del ListView.
//        mLeadsList = (ListView) root.findViewById(R.id.leads_list);
//
//        // Inicializar el adaptador con la fuente de datos.
//        mLeadsAdapter = new LeadsAdapter(getActivity(),
//                LeadsRepository.getInstance().getLeads());
//
//        //Relacionando la lista con el adaptador
//        mLeadsList.setAdapter(mLeadsAdapter);
//
//        // Eventos
//        mLeadsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Lead currentLead = mLeadsAdapter.getItem(position);
//                Toast.makeText(getActivity(),
//                        "Iniciar screen de detalle para: \n" + currentLead.getName(),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
////        setHasOptionsMenu(true);
//        return root;
//    }
//
////    @Override
////    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
////        super.onCreateOptionsMenu(menu, inflater);
////        inflater.inflate(R.menu.menu_leads_list, menu);
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        int id = item.getItemId();
////        if (id == R.id.action_delete_all) {
////            // Eliminar todos los leads
////            mLeadsAdapter.clear();
////            return true;
////        }
////        return super.onOptionsItemSelected(item);
////    }
//}

//todo
