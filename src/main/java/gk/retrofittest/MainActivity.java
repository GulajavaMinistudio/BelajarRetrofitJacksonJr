package gk.retrofittest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gk.retrofittest.models.ModelRespon;
import gk.retrofittest.models.ModelResponList;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Gulajava Ministudio on 11/13/15.
 */
public class MainActivity extends AppCompatActivity {


    private ProgressDialog progressbar;
    private TextView tekshasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintest);

        Button tombolambildatastring = (Button) findViewById(R.id.tombolambildata);
        tombolambildatastring.setOnClickListener(listenertombol);

        Button tombolambildatajakson = (Button) findViewById(R.id.tombolambildatajakson);
        tombolambildatajakson.setOnClickListener(listenertombol);

        Button tombollistdatajackson = (Button) findViewById(R.id.tombolambillistjackson);
        tombollistdatajackson.setOnClickListener(listenertombol);

        tekshasil = (TextView) findViewById(R.id.tekshasil);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    View.OnClickListener listenertombol = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.tombolambildata:

                    showProgress();

                    ambilDataStr();

                    break;

                case R.id.tombolambildatajakson:

                    showProgress();

                    ambilDataJackson();

                    break;

                case R.id.tombolambillistjackson :

                    showProgress();
                    ambilDataJacksonList();
                    break;
            }
        }
    };


    private void ambilDataJackson() {

        RestClientsJackson<ModelRespon> restClientsJackson = new RestClientsJackson<>(ModelRespon.class);
        Apis apisJaksonJR = restClientsJackson.getApiServicesJackson();
        Call<ModelRespon> calls = apisJaksonJR.getDataModels("GulajavaMinistudio", "BelajarVolleys");

        calls.enqueue(new Callback<ModelRespon>() {
            @Override
            public void onResponse(Response<ModelRespon> response, Retrofit retrofit) {

                progressbar.dismiss();
                ModelRespon modelRespon = response.body();

                if (response.isSuccess()) {

                    Log.w("SUKSES", " SUKSES JACKSON JR");

                    String responstr = "Results ID " + modelRespon.getId() + " , FULL NAME " + modelRespon.getFull_name() + " , REPO NAME " + modelRespon.getName();

                    tekshasil.setText(responstr);
                } else {
                    String result = "Response none";
                    tekshasil.setText(result);
                }

            }

            @Override
            public void onFailure(Throwable t) {

                t.printStackTrace();
                progressbar.dismiss();

                tekshasil.setText(t.getMessage());

            }
        });
    }




    private void ambilDataJacksonList() {

        RestClientsJackson<ModelResponList> restClientsJackson = new RestClientsJackson<>(ModelResponList.class);
        Apis apisJr = restClientsJackson.getApiServicesJacksonArray();

        Call<List<ModelResponList>> listCall = apisJr.getDataModelList();

        listCall.enqueue(new Callback<List<ModelResponList>>() {
            @Override
            public void onResponse(Response<List<ModelResponList>> response, Retrofit retrofit) {

                progressbar.dismiss();
                List<ModelResponList> listsModelRespon = response.body();

                if (response.isSuccess()) {

                    Log.w("SUKSES", " SUKSES JACKSON JR");

                    String responstr = "Results ID panjang array " + listsModelRespon.size();

                    tekshasil.setText(responstr);
                } else {
                    String result = "Response none";
                    tekshasil.setText(result);
                }


            }

            @Override
            public void onFailure(Throwable t) {


                t.printStackTrace();
                progressbar.dismiss();

                tekshasil.setText(t.getMessage());
            }
        });
    }






    private void ambilDataStr() {

        RestClientsJackson restClientsJackson = new RestClientsJackson();
        Apis apis = restClientsJackson.getApiServicesString();
        Call<String> callberita = apis.getDataStrings("GulajavaMinistudio", "BelajarVolleys");

        callberita.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {

                progressbar.dismiss();
                String string = response.body();

                if (response.isSuccess()) {

                    Log.w("RESPON OK", "RESPON OK String");
                    String results = "String result " + string;
                    tekshasil.setText(results);

                } else {
                    String results = "String result " + "None";
                    tekshasil.setText(results);
                }
            }

            @Override
            public void onFailure(Throwable t) {

                t.printStackTrace();
                progressbar.dismiss();
                tekshasil.setText(t.getMessage());

            }
        });
    }


    private void showProgress() {

        progressbar = new ProgressDialog(MainActivity.this);
        progressbar.setMessage("Ambil data...");
        progressbar.setCancelable(false);
        progressbar.show();

    }


}
