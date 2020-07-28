package com.example.storageretrieval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText2;
    String S1, S2;
    FileOutputStream fileOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
    }

    public void internal(View view) {
        S1 = editText.getText().toString();
        S2 = editText2.getText().toString();

        try {
            fileOutputStream = openFileOutput(S1, Context.MODE_APPEND);
            fileOutputStream.write(S2.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "Success!!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void external(View view) {
       /* S1=editText.getText().toString();
        S2=editText2.getText().toString();
        try {
            File sDcard= Environment.getExternalStorageDirectory();
            File dir = new File(sDcard.getAbsolutePath(),S1);
            dir.createNewFile();

            FileOutputStream fileOutputStream= new FileOutputStream(dir);
            OutputStreamWriter outputStreamWriter= new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(S2);
            fileOutputStream.close();
            Toast.makeText(this, "Save Success!!", Toast.LENGTH_SHORT).show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.i("External File",e.getMessage());
        }*/

        String filename = editText.getText().toString();
        String data = editText2.getText().toString();

        try {
            File myFile = new File("/sdcard/" + filename);
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);
            myOutWriter.close();
            fOut.close();
            Toast.makeText(getApplicationContext(), filename + "saved External", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void read_int(View view) {
        S1 = editText.getText().toString();
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(S1)));
            String tempst = "";
            while ((tempst = bufferedReader.readLine()) != null) {
                stringBuffer.append(tempst + '\n');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "No filename like this exists!!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error fetching the data", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "" + stringBuffer.toString(), Toast.LENGTH_SHORT).show();
    }


    public void readext(View view) {
        S1 = editText.getText().toString();
        StringBuffer stringBuffer = new StringBuffer();
        String aDatarow = "";
        String aBuffer = "";
        try {
            File myFile = new File("/sdcard/" + S1);
            FileInputStream fin = new FileInputStream(myFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fin));
            while ((aDatarow = bufferedReader.readLine()) != null) {
                aBuffer += aDatarow + '\n';
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File is not existing!!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "" + aBuffer, Toast.LENGTH_SHORT).show();

    }
}