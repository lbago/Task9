package androidlab.epam.com.task9;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OpenFileTask extends AsyncTask <File, Void, StringBuilder> {

    private ProgressBar progressBar;
    private EditText editText;

    public OpenFileTask(ProgressBar progressBar, EditText editText){
        super();
        this.progressBar=progressBar;
        this.editText=editText;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected StringBuilder doInBackground(File... params) {
        StringBuilder openedText = new StringBuilder();
        try {
            String str = "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(params[0]));
            while ((str = bufferedReader.readLine()) != null) {
                openedText.append(str);
            }
            bufferedReader.close();
            TimeUnit.SECONDS.sleep(3);
        } catch (IOException | InterruptedException e) {
            Log.d("!!!!!!!!!!!","ERROR");
            e.printStackTrace();
        }
        return openedText;
    }

    @Override
    protected void onPostExecute(StringBuilder text) {
        super.onPostExecute(text);
        editText.setText(text);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}

