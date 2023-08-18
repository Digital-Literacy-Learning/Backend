package com.springboot.democ.DAO;

import com.springboot.democ.Entity.Response;
import com.springboot.democ.Controller.ResponseRequest;
import com.springboot.democ.Respository.ResponseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseDAOImpl implements ResponseDAO{

    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseDAOImpl(ResponseRepository responseRepository)
    {
        this.responseRepository=responseRepository;
    }


    // WatchKey로 DB 안에 Response 객체가 없다는 것을 찾았을 경우 새로
    // DB안에 Response 객체를 만들어 넣기 위해 만든 매서드입니다.
    @Override
    @Transactional
    public Response insertResponse(ResponseRequest answer) {
        Response response1 = new Response(); //Response 생성
        response1.setVideokey(answer.getVideokey()); //Response watchkey 설정
        response1.setUserId(answer.getUserId());
        response1.setCategory(answer.getCategory());
        response1.setPublisher(answer.getPublisher());
        response1.setPostDate(answer.getFormattedPostDate());
        if (answer.getResponse1().equals("Y")) { //만약 프론트에서 온 답변이 Y일 경우
            response1.setYesResponse1(1); //Response객체의 YesResponse응답을 1로
            response1.setNoResponse1(0);
            response1.setTotal(response1.getNoResponse1()+ response1.getYesResponse1());
            if(answer.getResponse2().equals("Y")){
                response1.setYesResponse2(1); //Response객체의 YesResponse응답을 1로
                response1.setNoResponse2(0);
                response1.setTotal2(response1.getNoResponse2()+ response1.getYesResponse2());

            } else if (answer.getResponse2().equals("N")) {
                response1.setYesResponse2(0); //Response객체의 YesResponse응답을 1로
                response1.setNoResponse2(1);
                response1.setTotal2(response1.getNoResponse2()+ response1.getYesResponse2());

            }
            return response1;


        } else {
            response1.setNoResponse1(1);
            response1.setYesResponse1(0);
            response1.setTotal(response1.getNoResponse1() + response1.getYesResponse1());
            if(answer.getResponse2().equals("Y")){
                response1.setYesResponse2(1); //Response객체의 YesResponse응답을 1로
                response1.setNoResponse2(0);
                response1.setTotal2(response1.getNoResponse2()+ response1.getYesResponse2());

            } else if (answer.getResponse2().equals("N")) {
                response1.setYesResponse2(0); //Response객체의 YesResponse응답을 1로
                response1.setNoResponse2(1);
                response1.setTotal2(response1.getNoResponse2()+ response1.getYesResponse2());

            }
            return response1;

        }


    }



    // 이것은 만약 watchkey로 Response 객체가 조회되었을 경우
    // Response의 Yes와 No 값을 업데이트하기 위한 매서드입니다.
    @Override
    @Transactional
    public Response updateResponse(Response response , ResponseRequest answer) {
        //파라미터로 response객체와 프론트에서 온 answer 객체를 받습니다.
        if(answer.getResponse1().equals("Y")) {
            //만약 프론트에서 온 Response값이 Y일 경우 기존의 YesResponse에서 1을 더해줍니다.
            response.setYesResponse1(response.getYesResponse1() + 1);
            if(answer.getResponse2().equals("Y")){
                response.setYesResponse2(response.getYesResponse2() + 1);
            } else if (answer.getResponse2().equals("N")) {
                response.setNoResponse2(response.getNoResponse2()+1);
            }
            return response;
        }else{
            response.setNoResponse1(response.getNoResponse1()+1);
            if(answer.getResponse2().equals("Y")){
                response.setYesResponse2(response.getYesResponse2() + 1);
            } else if (answer.getResponse2().equals("N")) {
                response.setNoResponse2(response.getNoResponse2()+1);
            }
        }
        return response;
    }


}
