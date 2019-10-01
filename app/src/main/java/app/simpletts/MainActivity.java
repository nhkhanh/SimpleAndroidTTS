package app.simpletts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech textToSpeechSystem;
    private Button readButton;
    private EditText textEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readButton = findViewById(R.id.speak);
        textEditText = findViewById(R.id.text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        textToSpeechSystem = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeechSystem.setLanguage(new Locale("vi"));
                    readButton.setEnabled(true);
                }
                else
                    readButton.setEnabled(false);
            }
        });

//        textEditText.setText(Arrays.toString(Locale.getAvailableLocales()));
//        Log.d("MainActivity", Arrays.toString(Locale.getAvailableLocales()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeechSystem.shutdown();
    }

    public void onSpeakClicked(View view) {
        textToSpeechSystem.speak(textEditText.getText().toString(), TextToSpeech.QUEUE_ADD, null);
    }
}
