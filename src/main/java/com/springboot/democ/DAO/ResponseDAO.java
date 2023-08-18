package com.springboot.democ.DAO;

import com.springboot.democ.Entity.Response;
import com.springboot.democ.Controller.ResponseRequest;

public interface ResponseDAO {

    Response insertResponse(ResponseRequest answer);

    Response updateResponse(Response response,ResponseRequest answer);



}
