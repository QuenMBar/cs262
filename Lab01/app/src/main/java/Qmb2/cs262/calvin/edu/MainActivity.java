package Qmb2.cs262.calvin.edu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "Hello World");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
