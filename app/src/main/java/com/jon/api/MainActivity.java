package com.jon.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jon.api.Model.Employee;
import com.jon.api.api.EmployeeAPI;
import com.jon.api.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView tvOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall= employeeAPI.getAllEmployee();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
if (!response.isSuccessful()) {
    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            return;
}
List<Employee> empList=response.body();
for(Employee employee: empList){

    String data="";

    data+="Employee name:"+ employee.getEmployee_name()+"\n";
    data+="Employee age:"+ employee.getEmployee_age()+"\n";
    data+="Employee salary:"+ employee.getEmployee_salary()+"\n";
    data+="--------------------------------"+"\n";

    tvOutput.append(data);
}

}


            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("mero msg","onFailure"+ t.getLocalizedMessage());
            }
        });
    }
}
