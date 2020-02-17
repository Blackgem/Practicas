package info.androidtv.hplistview;

//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link LeadsFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link LeadsFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class LeadsFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public LeadsFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment LeadsFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static LeadsFragment newInstance(String param1, String param2) {
//        LeadsFragment fragment = new LeadsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_leads, container, false);
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//}

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
import android.widget.ListView;
import android.widget.Toast;


/**
 * Vista para los leads del CRM
 */
public class LeadsFragment extends Fragment {
    private ListView mLeadsList;
    private LeadsAdapter mLeadsAdapter;

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

        // Inicializar el adaptador con la fuente de datos.
        mLeadsAdapter = new LeadsAdapter(getActivity(),
                LeadsRepository.getInstance().getLeads());

        //Relacionando la lista con el adaptador
        mLeadsList.setAdapter(mLeadsAdapter);

        // Eventos
        mLeadsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Lead currentLead = mLeadsAdapter.getItem(position);
                Toast.makeText(getActivity(),
                        "Iniciar screen de detalle para: \n" + currentLead.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

//        setHasOptionsMenu(true);
        return root;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_leads_list, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_delete_all) {
//            // Eliminar todos los leads
//            mLeadsAdapter.clear();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

//todo


//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//
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
//        setHasOptionsMenu(true);
//        return root;
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_leads_list, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_delete_all) {
//            // Eliminar todos los leads
//            mLeadsAdapter.clear();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
