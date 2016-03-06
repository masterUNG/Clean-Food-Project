package suthasidev.cleanfoodproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RecipeDetailActivity extends AppCompatActivity {

    //Explicit
    private TextView recipeTextView, descripTextView;
    private ListView ingredientListView, howToListView;
    private ImageView imageView;
    private String recipeString, descripString, ingredientString,
            howtoString, imageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //Bind Widget
        bindWidget();

        //Receive Value From Intent
        receiveValue();

        //Show View
        showView();

    }   // Main Method

    private void showView() {

        //Show TextView
        recipeTextView.setText(recipeString);
        descripTextView.setText(descripString);

        //Show Image
        Picasso.with(RecipeDetailActivity.this).load(imageString)
                .resize(180, 180).into(imageView);



    }   // showView

    private void receiveValue() {
        recipeString = getIntent().getStringExtra("nameRecipe");
        imageString = getIntent().getStringExtra("urlRecipe");
        ingredientString = getIntent().getStringExtra("Ingredient");
        howtoString = getIntent().getStringExtra("HowTo");
        descripString = getIntent().getStringExtra("Descrip");
    }

    private void bindWidget() {
        recipeTextView = (TextView) findViewById(R.id.textView3);
        descripTextView = (TextView) findViewById(R.id.textView7);
        ingredientListView = (ListView) findViewById(R.id.listView2);
        howToListView = (ListView) findViewById(R.id.listView3);
        imageView = (ImageView) findViewById(R.id.imageView2);


    }

}   // Main Class
