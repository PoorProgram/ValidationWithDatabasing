package corbita.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class CreateAccount extends AppCompatActivity {

    Button button;


    EditText etUsername, etPassword,etfullname;
    String username, password,fullname;
    int formsuccess,userid;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        db = new DbHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etfullname = findViewById(R.id.etFullname);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuvalid,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.btnSave:
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                fullname = etfullname.getText().toString();
                formsuccess =3;

                if (username.equals(""))
                {
                    etUsername.setError("This field is required");
                    formsuccess--;
                }

                if (password.equals(""))
                {
                    etPassword.setError("This field is required");
                    formsuccess--;
                }

                if (fullname.equals(""))
                {
                    etfullname.setError("This field is required");
                    formsuccess--;
                }

                if (formsuccess == 3)
                {
                    HashMap<String, String> map_user = new HashMap();
                    map_user.put(db.TBL_USERNAME, username);
                    map_user.put(db.TBL_PASSWORD, password);
                    map_user.put(db.TBL_FULLNAME, fullname);
                    userid = db.createUser(map_user);
                    if(userid < 1) {
                        Toast.makeText(this, "USER SUCCESSFULLY CREATED", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        etUsername.setError("Username already existed");
                    }

                }
                break;
            case R.id.btnCancel:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
