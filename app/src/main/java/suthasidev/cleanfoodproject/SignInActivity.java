package suthasidev.cleanfoodproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Bind Widget
        bindWidget();

    }   // Main Method

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
    }

    public void clickSignInAuthen(View view) {

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (userString.equals("") || passwordString.equals("")) {
            //Have Space
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(SignInActivity.this, "มีช่องว่าง", "กรุณากรอกทุกช่อง คะ");

        } else {
            //No Space
            checkUser();

        }

    }   // clickSignInAuthen

    private void checkUser() {

        try {

            MyManage myManage = new MyManage(this);
            String[] myResultStrings = myManage.searchUser(userString);

            //Check Password
            if (passwordString.equals(myResultStrings[2])) {
                //Password True


            } else {
                //Password False
                MyAlertDialog myAlertDialog = new MyAlertDialog();
                myAlertDialog.myDialog(SignInActivity.this, "Password ผิด",
                        "Password ผิด ลองพิมพ์ ใหม่อีกที คะ");
            }

        } catch (Exception e) {
            //No This User
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(SignInActivity.this, "ไม่มี User",
                    "ไม่มี " + userString + " ในฐานข้อมูลของเรา");
        }


    }   // checkUser

}   // Main Class
