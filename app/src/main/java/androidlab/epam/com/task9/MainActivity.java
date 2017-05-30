package androidlab.epam.com.task9;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    final int REQUEST_CODE = 777;

    private Button openButton;
    private Button saveButton;
    private EditText editText;
    private ProgressBar progressBar;
    private File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openButton = (Button) findViewById(R.id.open_button);
        saveButton = (Button) findViewById(R.id.save_button);
        editText = (EditText) findViewById(R.id.edit_text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openText();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText();
            }
        });
    }

    private void openText() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void saveText() {
        SaveFileTask saveFileTask = new SaveFileTask(progressBar, editText);
        saveFileTask.execute(file);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Uri uri = Uri.parse(data.toString());
            String[] str = uri.getPath().split(" ");
            file = new File(str[0]);
            OpenFileTask openFileTask = new OpenFileTask(progressBar, editText);
            openFileTask.execute(file);
        }
    }
}