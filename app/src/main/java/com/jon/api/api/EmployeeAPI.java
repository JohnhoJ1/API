package com.jon.api.api;


import com.jon.api.Model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeAPI {
    @GET("employees")
    Call<List<Employee>> getAllEmployee();
}

