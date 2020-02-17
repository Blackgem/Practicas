package info.androidtv.hplistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Repositorio ficticio de leads
 */
public class LeadsRepository {
    private static LeadsRepository repository = new LeadsRepository();
    private HashMap<String, Lead> leads = new HashMap<>();

    public static LeadsRepository getInstance() {
        return repository;
    }

    private LeadsRepository() {
        saveLead(new Lead("Alexander Pierrot", "CEO", "Insures S.O.", R.drawable.lead_photo_1));
        saveLead(new Lead("Carlos Lopez", "Asistente", "Hospital Blue", R.drawable.lead_photo_2));
        saveLead(new Lead("Sara Bonz", "Directora de Marketing", "Electrical Parts ltd", R.drawable.lead_photo_3));
        saveLead(new Lead("Liliana Clarence", "Diseñadora de Producto", "Creativa App", R.drawable.lead_photo_4));
        saveLead(new Lead("Benito Peralta", "Supervisor de Ventas", "Neumáticos Press", R.drawable.lead_photo_5));
        saveLead(new Lead("Juan Jaramillo", "CEO", "Banco Nacional", R.drawable.lead_photo_6));
        saveLead(new Lead("Christian Steps", "CTO", "Cooperativa Verde", R.drawable.lead_photo_7));
        saveLead(new Lead("Alexa Giraldo", "Lead Programmer", "Frutisofy", R.drawable.lead_photo_8));
        saveLead(new Lead("Linda Murillo", "Directora de Marketing", "Seguros Boliver", R.drawable.lead_photo_9));
        saveLead(new Lead("Lizeth Astrada", "CEO", "Concesionario Motolox", R.drawable.lead_photo_10));
    }

    private void saveLead(Lead lead) {
        leads.put(lead.getId(), lead);
    }

    public List<Lead> getLeads() {
        return new ArrayList<>(leads.values());
    }
}

//todo

//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Repositorio ficticio de leads
// */
//public class LeadsRepository {
//    private static LeadsRepository repository = new LeadsRepository();
//    private HashMap<String, Lead> leads = new HashMap<>();
//
//    public static LeadsRepository getInstance() {
//        return repository;
//    }
//
//    private LeadsRepository() {
//        saveLead(new Lead("Alexander Pierrot", "CEO", "Insures S.O.", R.drawable.lead_photo_1));
//        saveLead(new Lead("Carlos Lopez", "Asistente", "Hospital Blue", R.drawable.lead_photo_2));
//        saveLead(new Lead("Sara Bonz", "Directora de Marketing", "Electrical Parts ltd", R.drawable.lead_photo_3));
//        saveLead(new Lead("Liliana Clarence", "Diseñadora de Producto", "Creativa App", R.drawable.lead_photo_4));
//        saveLead(new Lead("Benito Peralta", "Supervisor de Ventas", "Neumáticos Press", R.drawable.lead_photo_5));
//        saveLead(new Lead("Juan Jaramillo", "CEO", "Banco Nacional", R.drawable.lead_photo_6));
//        saveLead(new Lead("Christian Steps", "CTO", "Cooperativa Verde", R.drawable.lead_photo_7));
//        saveLead(new Lead("Alexa Giraldo", "Lead Programmer", "Frutisofy", R.drawable.lead_photo_8));
//        saveLead(new Lead("Linda Murillo", "Directora de Marketing", "Seguros Boliver", R.drawable.lead_photo_9));
//        saveLead(new Lead("Lizeth Astrada", "CEO", "Concesionario Motolox", R.drawable.lead_photo_10));
//    }
//
//    private void saveLead(Lead lead) {
//        leads.put(lead.getId(), lead);
//    }
//
//    public List<Lead> getLeads() {
//        return new ArrayList<>(leads.values());
//    }
//}
