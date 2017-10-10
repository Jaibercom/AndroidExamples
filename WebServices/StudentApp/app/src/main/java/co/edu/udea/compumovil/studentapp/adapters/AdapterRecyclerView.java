package co.edu.udea.compumovil.studentapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import co.edu.udea.compumovil.studentapp.R;
import co.edu.udea.compumovil.studentapp.activities.MainActivity;
import co.edu.udea.compumovil.studentapp.models.Student;
import co.edu.udea.compumovil.studentapp.webservices.ConfigURL;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jaiber on 07/09/2017.
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.StudentViewHolder>{

    private List<Student> students;
    private Context ctx;

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRecyclerView(List<Student> persons) {
        this.students = persons;
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRecyclerView() {
        this.students = new ArrayList<>();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterRecyclerView(Context ctx) {
        this.students = new ArrayList<>();
        this.ctx = ctx;

    }


    // Create new views (invoked by the layout manager)
    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_student, parent, false);
        StudentViewHolder pvh = new StudentViewHolder(view);
        return pvh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(StudentViewHolder holder, int pos) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.studentName.setText(students.get(pos).getFirstname() + " "+ students.get(pos).getLastName());

        String gender = students.get(pos).getGender();
        if(gender.equals("M")){
            holder.studentGender.setText("Masculino");
        }else
            holder.studentGender.setText("Femenino");

        //holder.studentPhoto.setImageResource(students.get(pos).getPhoto());
        Glide.with(ctx)
                .load(ConfigURL.URL_CONTAINER_DOWN.concat(String.valueOf(students.get(pos).getId())).concat(students.get(pos).getPhoto()))
                .placeholder(R.drawable.person2)
                .into(holder.studentPhoto);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //int size=0;
        if (students != null)
            return students.size();
        else
            return 0;
    }


    public void updateList (List<Student> items) {
        if (items != null && items.size() > 0) {
            if(students != null)
                students.clear();
            students.addAll(items);
            notifyDataSetChanged();
        }
    }

    //Clase necesaria para la implementación del RecyclerView
    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView cardView;
        public TextView studentName;
        public TextView studentGender;
        public ImageView studentPhoto;

        StudentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            studentName = (TextView) itemView.findViewById(R.id.student_name);
            studentGender = (TextView) itemView.findViewById(R.id.person_gender);
            studentPhoto = (ImageView) itemView.findViewById(R.id.student_photo);
        }

        private void removeItem(int position) {
            students.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, students.size());
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            removeItem(pos);
            Log.d("AdapterRecyclerView", "onClick: " + pos + "  Name: "+ studentName.getText() );
            Toast.makeText(itemView.getContext(), "Hello: "+ studentName.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
