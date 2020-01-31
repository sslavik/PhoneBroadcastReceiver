package com.example.phonebroadcastreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // SE CREA UNA CONSTANTE POR CADA PERMISO QUE NECESITE LA APP
    public final int PERMISSION_REQUEST_PHONE_STATE = 1; // CON ESTE CODIGO GESTIONAMOS LA ID DE LA PETICION
    public final int PERMISSION_REQUEST_CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Se Chequea si tiene el permisos concedidos
        requestPhonePermission();
    }

    /**
     * Solicita el permiso al telefono para acceso a los PHONE_STATE ( ioLlamadas )
     */
    private void requestPhonePermission() {
        if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA}, PERMISSION_REQUEST_PHONE_STATE);
        }
    }

    // ESTE METODO SOBREESCRITO SE EJECUTA SIEMPRE DESPUÉS DE UN requestPermissions().
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_PHONE_STATE:
                // ESTO MISMO SE PUEDE PREGUNTAR DIRECTAMENTAMENTE OTRA VEZ A
                // checkSelfPermissions(permissions[0]);
                // TENEMOS QUE COMPROBAR EL TAMAÑO DEL grantResults PARA QUE SI CAMBIA DE ORIENTACIÓN NO DE ERROR POR ARRAY OUT OF BOUND
                // Ya que vuelve a ejecutar el codigo y tienes que esperar a que vuevla a hacer el "requestPermissions"
                if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "No tienes permiso de llamadas", Toast.LENGTH_SHORT).show();
                }
                break;
            case PERMISSION_REQUEST_CAMERA:
                if(grantResults.length > 0 && grantResults[1] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "No tienes permiso de camara", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
