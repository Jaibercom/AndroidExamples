package co.edu.udea.compumovil.linearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txtMessage;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout_1);

        txtMessage = (EditText) findViewById(R.id.message_textview_id);
        btnSend = (Button) findViewById(R.id.send_button_id);

        btnSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_button_id:
                //Mostar mensaje
                Toast.makeText(this, txtMessage.getText().toString(), Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
