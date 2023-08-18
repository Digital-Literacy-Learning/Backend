package com.springboot.democ.DAO;

import com.springboot.democ.Controller.ResponseRequest;
import com.springboot.democ.Entity.Response;
import com.springboot.democ.Entity.Videokeydata;

public interface VideokeyDAO {

    Videokeydata insertResponse(ResponseRequest answer);

    Videokeydata updateResponse(Videokeydata response,ResponseRequest answer);


}
