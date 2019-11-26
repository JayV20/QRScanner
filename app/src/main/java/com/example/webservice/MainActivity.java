package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

/**
 * @author Jay Vega
 */
public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Button btnScanner;
    private RadioButton rad1;
    private RadioButton rad2;
    private RadioGroup radG;
    private TextView viewDatos;
    int id_asis=1;
    //establece conexion con el web service.
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScanner=(Button)findViewById(R.id.btnscan);
        rad1=(RadioButton)findViewById(R.id.entrada);
        rad2=(RadioButton)findViewById(R.id.salida);
        radG=(RadioGroup) findViewById(R.id.rg);
        viewDatos=(TextView)findViewById(R.id.viewDatos);
        btnScanner.setOnClickListener(OnClick);
        radG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radio, int checkedId) {
                int id = radG.getCheckedRadioButtonId();
                if(id==R.id.entrada){
                    id_asis=1;//entrada
                    System.out.println(id_asis);
                }else if(id==R.id.salida){
                    id_asis=2;//salida
                    System.out.println(id_asis);
                }
            }
        });

        //Verificamos que la app tenga permisos de escritura
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null) {
            String QR= result.getContents();//obtiene la información del QR.
            if(QR == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                viewDatos.setText("No se escaneo el QR");
            } else {
                viewDatos.setText("El código QR tiene: \n "+QR);
                String []parts=QR.split(";");//Separa el código QR para obtener la información.
                Toast.makeText(this, "nombre:"+parts[0], Toast.LENGTH_LONG).show();
                cargarWebService(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],parts[6],String.valueOf(id_asis));

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


    private void cargarWebService(String nombre,String app,String apm, String matricula, String grupo,String turno,String estudio, String acceso){
        try {
                //String url_local = "http://192.168.1.82/logisticaApp/control2.php?nombre=" + nombre + "&app=" + app + "&apm=" + apm + "&matricula=" + matricula + "&grupo=" + grupo + "&turno=" + turno + "&estudio=" + estudio + "&acceso=" + acceso;
                //url_local = url_local.replace(" ", "%20");
                //ponemos la url del servidor o url local.
                String url_server="https://semana-ti.atomixti.com/LogisticaApp/control2.php?nombre=" + nombre + "&app=" + app + "&apm=" + apm + "&matricula=" + matricula + "&grupo=" + grupo + "&turno=" + turno + "&estudio=" + estudio + "&acceso=" + acceso;
                //sustituimos los espacios por un %20 por la url.
                url_server = url_server.replace(" ", "%20");
                //envio de la información al php como objeto Json. por metodo get.
                jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url_server, null, this, this);
                //realizamos la cola para insertar los datos.
                request = Volley.newRequestQueue(this);
                //añadimos la información al request.
                request.add(jsonObjectRequest);
            }catch(Exception e){
                Toast.makeText(this,"Ha ocurrido un error al enviar la información",Toast.LENGTH_LONG).show();
            }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"Error en la inserción",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this,"Se ha registrado correctamente",Toast.LENGTH_LONG).show();
    }
    //Método para accionar los botones
    private View.OnClickListener OnClick=new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnscan:
                    try {
                        IntentIntegrator intent = new IntentIntegrator(MainActivity.this);
                        intent.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                        intent.setPrompt("Scan");
                        intent.setCameraId(0);
                        intent.setBeepEnabled(true);
                        intent.initiateScan();
                    }catch(Exception e){

                    }
                    break;
            }
        }
    };
}
