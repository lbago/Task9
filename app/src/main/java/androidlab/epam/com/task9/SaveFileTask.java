package androidlab.epam.com.task9;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class SaveFileTask extends AsyncTask<File, Void, Void> {
    private ProgressBar progressBar;
    private EditText editText;
    private String savedText;

    public SaveFileTask(ProgressBar progressBar, EditText editText){
        super();
        this.progressBar=progressBar;
        this.editText=editText;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        savedText = editText.getText().toString();
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    protected Void doInBackground(File... params) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(params[0]));
            bufferedWriter.write(savedText);
            bufferedWriter.flush();
            bufferedWriter.close();
            TimeUnit.SECONDS.sleep(3);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}
