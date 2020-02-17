package info.androidtv.hplistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import java.util.List;

///**
// * Adaptador de leads
// */
//public class LeadsAdapter extends ArrayAdapter<Lead> {
//    public LeadsAdapter(Context context, List<Lead> objects) {
//        super(context, 0, objects);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Obtener inflater.
//        LayoutInflater inflater = (LayoutInflater) getContext()
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        // ¿Existe el view actual?
//        if (null == convertView) {
//            convertView = inflater.inflate(
//                    R.layout.list_item_lead,
//                    parent,
//                    false);
//        }
//
//        // Referencias UI.
//        ImageView avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
//        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
//        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
//        TextView company = (TextView) convertView.findViewById(R.id.tv_company);
//
//        // Lead actual.
//        Lead lead = getItem(position);
//
//        // Setup.
////        todo
////        Glide.with(getContext()).load(lead.getImage()).into(avatar);
//        name.setText(lead.getName());
//        title.setText(lead.getTitle());
//        company.setText(lead.getCompany());
//
//        return convertView;
//    }
//
//}


/**
 * Adaptador de leads
 */
public class LeadsAdapter extends ArrayAdapter<Lead> {
    public LeadsAdapter(Context context, List<Lead> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;

        // ¿Ya se infló este view?
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            convertView = inflater.inflate(
                    R.layout.list_item_lead,
                    parent,
                    false);

            holder = new ViewHolder();
            holder.avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.company = (TextView) convertView.findViewById(R.id.tv_company);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Lead actual.
        Lead lead = getItem(position);

        // Setup.
        holder.name.setText(lead.getName());
        holder.title.setText(lead.getTitle());
        holder.company.setText(lead.getCompany());
//        Glide.with(getContext()).load(lead.getImage()).into(holder.avatar);

        return convertView;
    }

    static class ViewHolder {
        ImageView avatar;
        TextView name;
        TextView title;
        TextView company;
    }
}


//todo
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
////import com.bumptech.glide.Glide;
//
//import java.util.List;
//
///**
// * Adaptador de leads
// */
//public class LeadsAdapter extends ArrayAdapter<Lead> {
//    public LeadsAdapter(Context context, List<Lead> objects) {
//        super(context, 0, objects);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Obtener inflater.
//        LayoutInflater inflater = (LayoutInflater) getContext()
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        ViewHolder holder;
//
//        // ¿Ya se infló este view?
//        if (null == convertView) {
//            //Si no existe, entonces inflarlo con image_list_view.xml
//            convertView = inflater.inflate(
//                    R.layout.list_item_lead,
//                    parent,
//                    false);
//
//            holder = new ViewHolder();
//            holder.avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
//            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
//            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
//            holder.company = (TextView) convertView.findViewById(R.id.tv_company);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        // Lead actual.
//        Lead lead = getItem(position);
//
//        // Setup.
//        holder.name.setText(lead.getName());
//        holder.title.setText(lead.getTitle());
//        holder.company.setText(lead.getCompany());
////        Glide.with(getContext()).load(lead.getImage()).into(holder.avatar);
//
//        return convertView;
//    }
//
//    static class ViewHolder {
//        ImageView avatar;
//        TextView name;
//        TextView title;
//        TextView company;
//    }
//}
