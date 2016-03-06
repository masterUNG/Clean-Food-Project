package suthasidev.cleanfoodproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private TextView showUserTextView;
    private ListView recipeListView;
    private String nameUserString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bind Widget
        bindWidget();

        //Show Name
        showName();


    }   // Main Method

    private void showName() {

        //Receive Value From Intent
        nameUserString = getIntent().getStringExtra("userName");
        showUserTextView.setText("Welcome " + nameUserString);

    }   // showName

    private void bindWidget() {
        showUserTextView = (TextView) findViewById(R.id.textView);
        recipeListView = (ListView) findViewById(R.id.listView);
    }

}   // Main Class
