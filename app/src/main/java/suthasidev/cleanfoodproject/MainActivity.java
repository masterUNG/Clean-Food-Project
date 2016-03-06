package suthasidev.cleanfoodproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private MyManage objMyManage;

    GridView gridView;

    public int[] objImage = new int[]{R.drawable.android,
            R.drawable.marshmallow, R.drawable.lollipop, R.drawable.kitkat,
            R.drawable.jelly_bean, R.drawable.ice_cream_sandwich};

    public String[] objStrings = {"android-logo", "marshmallow-android",
            "lollipop-android", "kitkat-android", "jelly-bean-android",
            "ice-cream-sandwich-android"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create
        objMyManage = new MyManage(this);

        //Test Add Value
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();

        //synchronize JSON to SQLite
        synJSONtoSQLite();

        //Set Gridview Adepter
        setGridview();


    }  //Main Method

    public void clickSignIn(View view) {
        startActivity(new Intent(MainActivity.this, SignInActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        deleteAllSQLite();
        synJSONtoSQLite();

    }

    private void setGridview() {
        GridviewAdapter adapter = new GridviewAdapter(MainActivity.this, objStrings, objImage);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Your Clicked at" + objStrings[position], Toast.LENGTH_LONG).show();
            }
        });
    }


    private void synJSONtoSQLite() {

        //change policy
        StrictMode.ThreadPolicy MyPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(MyPolicy);

        int intTABLE = 0;
        while (intTABLE <= 2) {
            //InputStream
            InputStream objInputStream = null;
            String[] urlStrings = new String[3];
            urlStrings[0] = "http://swiftcodingthai.com/tan/php_get_user.php";
            urlStrings[1] = "http://swiftcodingthai.com/tan/php_get_recipe.php";
            urlStrings[2] = "http://swiftcodingthai.com/tan/php_get_restaurant.php";
            String tag = "CleanFood";

            try {
                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost(urlStrings[intTABLE]);
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d(tag, "InputStream ==> " + e.toString());

            }

            //JSON String
            String strJSON = null;
            try {

                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while ((strLine = objBufferedReader.readLine()) != null) {
                    objStringBuilder.append(strLine);

                }   //while

                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d(tag, "strJSON ==> " + e.toString());
            }

            //Update SQLite
            try {
                JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length(); i++) {
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);

                    switch (intTABLE) {
                        case 0:
                            //userTABLE
                            String strUser = jsonObject.getString(MyManage.column_User);
                            String strPassword = jsonObject.getString(MyManage.column_Password);
                            String strName = jsonObject.getString(MyManage.column_Name);
                            objMyManage.addUser(strUser, strPassword, strName);
                            break;

                        case 1:
                            //recipeTABLE
                            String strRecipe = jsonObject.getString(MyManage.column_Recipe);
                            String strIngredients = jsonObject.getString(MyManage.column_Ingredients);
                            String strHowTo = jsonObject.getString(MyManage.column_HowTo);
                            String strDescription = jsonObject.getString(MyManage.column_Description);
                            String strImg = jsonObject.getString(MyManage.column_ImageRecipe);
                            String strNameComment = jsonObject.getString(MyManage.column_NameComment);
                            String strComment = jsonObject.getString(MyManage.column_Comment);
                            objMyManage.addRecipe(strRecipe, strIngredients, strHowTo, strDescription, strImg, strNameComment, strComment);
                            break;

                        case 2:
                            //restaurantTABLE
                            String strRestaurant = jsonObject.getString(MyManage.column_Restaurant);
                            String strImgRes = jsonObject.getString(MyManage.column_ImageRestaurant);
                            String strPhone = jsonObject.getString(MyManage.column_Phone);
                            String strAddress = jsonObject.getString(MyManage.column_Address);
                            String strWeb = jsonObject.getString(MyManage.column_Website);
                            String strLat = jsonObject.getString(MyManage.column_Lat);
                            String strLng = jsonObject.getString(MyManage.column_Lng);
                            objMyManage.addRestaurant(strRestaurant, strImgRes, strPhone, strAddress, strWeb, strLat, strLng);
                            break;
                    }

                }   //for

            } catch (Exception e) {
                Log.d(tag, "Update => " + e.toString());
            }

            intTABLE += 1;
        }   //while

    }   //synJSONtoSQLite

    public void clickSignUpMain(View view) {

        startActivity(new Intent(MainActivity.this, RegisterActivity.class));

    } //clickSignUpMain

    private void deleteAllSQLite() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);

        objSqLiteDatabase.delete(MyManage.table_user, null, null);
        objSqLiteDatabase.delete(MyManage.table_recipe, null, null);
        objSqLiteDatabase.delete(MyManage.table_restaurant, null, null);


    } //deleteAllSQLite

    private void testAddValue() {
        objMyManage.addUser("user", "password", "name");
        objMyManage.addRecipe("recipe", "ingredients", "How-To",
                "description", "Image", "name", "comment");
        objMyManage.addRestaurant("Res", "ImgRes", "phone", "Address",
                "Website", "Latitude", "Longtitude");


    } //testAddValue
} //Main Class
