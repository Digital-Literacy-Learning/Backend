package com.springboot.democ.DAO;


import com.springboot.democ.Controller.ResponseRequest;
import com.springboot.democ.Entity.Response;
import com.springboot.democ.Entity.Videokeydata;
import com.springboot.democ.Respository.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideokeyDAOImpl implements VideokeyDAO{

    private final VideoRepository videoRepository;

    @Autowired
    public VideokeyDAOImpl(VideoRepository videoRepository)
    {
        this.videoRepository = videoRepository;
    }

    @Override
    @Transactional
    public Videokeydata insertResponse(ResponseRequest answer) {

            Videokeydata videokeydata = new Videokeydata(); //Response 생성
            videokeydata.setVideokey(answer.getVideokey()); //Response watchkey 설정
            if (answer.getResponse1().equals("Y")) { //만약 프론트에서 온 답변이 Y일 경우
                videokeydata.setYesResponse1(1); //Response객체의 YesResponse응답을 1로
                videokeydata.setNoResponse1(0);
                videokeydata.setTotal1(videokeydata.getNoResponse1()+ videokeydata.getYesResponse1());
                if(answer.getResponse2().equals("Y")){
                    videokeydata.setYesResponse2(1); //Response객체의 YesResponse응답을 1로
                    videokeydata.setNoResponse2(0);
                    videokeydata.setTotal2(videokeydata.getNoResponse2()+ videokeydata.getYesResponse2());

                } else if (answer.getResponse2().equals("N")) {
                    videokeydata.setYesResponse2(0); //Response객체의 YesResponse응답을 1로
                    videokeydata.setNoResponse2(1);
                    videokeydata.setTotal2(videokeydata.getNoResponse2()+ videokeydata.getYesResponse2());

                }
                return videokeydata;


            } else {
                videokeydata.setNoResponse1(1);
                videokeydata.setYesResponse1(0);
                videokeydata.setTotal1(videokeydata.getNoResponse1() + videokeydata.getYesResponse1());
                if(answer.getResponse2().equals("Y")){
                    videokeydata.setYesResponse2(1); //Response객체의 YesResponse응답을 1로
                    videokeydata.setNoResponse2(0);
                    videokeydata.setTotal2(videokeydata.getNoResponse2()+ videokeydata.getYesResponse2());

                } else if (answer.getResponse2().equals("N")) {
                    videokeydata.setYesResponse2(0); //Response객체의 YesResponse응답을 1로
                    videokeydata.setNoResponse2(1);
                    videokeydata.setTotal2(videokeydata.getNoResponse2()+ videokeydata.getYesResponse2());

                }
                return videokeydata;

            }


        }



        // 이것은 만약 watchkey로 Response 객체가 조회되었을 경우
        // Response의 Yes와 No 값을 업데이트하기 위한 매서드입니다.
        @Override
        @Transactional
        public Videokeydata updateResponse(Videokeydata response , ResponseRequest answer) {
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



