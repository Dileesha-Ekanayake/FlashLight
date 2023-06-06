package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    CameraManager cameraM;
    String getCameraID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton btnFlash = this.findViewById(R.id.btnFlash);
        ImageView background = this.findViewById(R.id.background);

        cameraM = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            getCameraID = cameraM.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        btnFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean btnFlashState = btnFlash.isChecked();
                if(btnFlashState == true){
                    try {
                        cameraM.setTorchMode(getCameraID, true);
                        btnFlash.setBackgroundColor(Color.GREEN);
                        background.setVisibility(View.VISIBLE);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }if (btnFlashState == false){
                    try {
                        cameraM.setTorchMode(getCameraID, false);
                        btnFlash.setBackgroundColor(Color.WHITE);
                        background.setVisibility(View.INVISIBLE);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}