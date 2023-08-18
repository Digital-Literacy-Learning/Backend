package com.springboot.democ.Controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.democ.DAO.ResponseDAO;
import com.springboot.democ.DAO.VideokeyDAO;
import com.springboot.democ.DTO.OpinionDTO;
import com.springboot.democ.DTO.ResponseDTO;
import com.springboot.democ.DTO.SourceDTO;
import com.springboot.democ.DTO.VideoDTO;
import com.springboot.democ.Entity.Publisher;
import com.springboot.democ.Entity.Response;
import com.springboot.democ.Entity.Videokeydata;
import com.springboot.democ.Respository.PublisherRepository;
import com.springboot.democ.Respository.ResponseRepository;
import com.springboot.democ.Respository.VideoRepository;
import com.springboot.democ.Service.ProcessGPT;
import com.springboot.democ.Service.ProcessGPT2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ResponseController {

    private final ResponseRepository responseRepository;
    private final VideoRepository videoRepository;

    private final PublisherRepository publisherRepository;

    private final ResponseDAO responseDAO;

    private final ResponseDTO responseDTO;

    private final VideokeyDAO videokeyDAO;


    @Autowired
    public ResponseController(ResponseRepository responseRepository, VideoRepository videoRepository,PublisherRepository publisherRepository, ResponseDAO responseDAO, ResponseDTO responseDTO, VideokeyDAO videokeyDAO) {
        this.responseRepository = responseRepository;
        this.videoRepository = videoRepository;
        this.publisherRepository = publisherRepository;
        this.responseDAO = responseDAO;
        this.responseDTO = responseDTO;
        this.videokeyDAO = videokeyDAO;


    }

    @Operation(summary = "사용자의 응답을 저장", description = "사용자와 영상에 대한 정보를 보내주면 사용자와 영상의 정보와, 영상에 따른 사용자들의 응답을 저장합니다. ")
    @CrossOrigin(origins = "*")
    @PostMapping("/save-responses")
    public ResponseEntity<?> ReturnResponse(
            @Parameter(description = "영상 키 , 사용자의 Y/N 응답", required = true, example =
                    "{ userId : 사용자 id, videokey : 영상 key 값 , response1: Y, response2 : N, category : 영상 카테고리, publisher : 게시자 , postDate : 20220309 }")
            @RequestBody ResponseRequest request) {
        String PostWatchkey = request.getVideokey();
        String UserId = request.getUserId();
        System.out.println(PostWatchkey);
        try {
            Response existingresponses = responseRepository.findByUserIdAndVideokey(UserId, PostWatchkey);
            Videokeydata videokeydata = videoRepository.findByvideokey(PostWatchkey);

            if (existingresponses != null) {

                return ResponseEntity.ok("{\"message\": \"이미 응답이 저장되어 있습니다. 중복 응답은 저장하지 않습니다.\"}");

            }
            else { //Response객체가 없으니 만들어야함
                Response response2 = responseDAO.insertResponse(request);
                responseRepository.save(response2);// Response객체 만들어서 행에 저장
                if(videokeydata != null) {
                    videokeyDAO.updateResponse(videokeydata, request);
                    return ResponseEntity.ok("{\"message\": \"Response 객체를 새로 만들어 저장하고, videokey에 따른 응답값을 업데이트 했습니다.\"}");

                }
                else{
                    Videokeydata videokeydata2 = videokeyDAO.insertResponse(request);
                    videoRepository.save(videokeydata2);
                    return ResponseEntity.ok("{\"message\": \"새로운 Response 객체를 생성하여 저장하고, Videokey 객체를 생성하여 저장하였습니다.\"}");
                }


            }



        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());

        }
    }



    @Operation(summary = "videokey를 보내면 사용자들의 응답 전송", description = "영상에 따른 사용자들의 응답을 전송합니다. ")
    @CrossOrigin(origins = "*")
    @PostMapping("/viewanswer-byvideokey")
    public ResponseEntity<?> videoResponse(
            @Parameter(description = "영상 키" , required = true, example = "{videokey : 영상 키 값 }")
            @RequestBody VideoRequest videoRequest){
        try{
        String Postwatchkey = videoRequest.getVideokey();
        Videokeydata videokeydata = videoRepository.findByvideokey(Postwatchkey);
        ResponseDTO responseDTO1 = new ResponseDTO(
                videokeydata.getVideokey(),
                videokeydata.getYesResponse1(),
                videokeydata.getNoResponse1(),
                videokeydata.getYesResponse2(),
                videokeydata.getNoResponse2(),
                videokeydata.getTotal1(),
                videokeydata.getTotal2()

        );
            return ResponseEntity.ok(responseDTO1);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("it has error");
        }



    }



    @Operation(summary = "videokey를 보내면 사용자들의 응답 전송", description = "영상에 따른 출처 여부에 대한 사용자들의 응답을 전송합니다. ")
    @CrossOrigin(origins = "*")
    @PostMapping("/view-source")
    public ResponseEntity<SourceDTO> sourceResponse(@RequestBody VideoRequest request){

        try{
            String Postwatchkey = request.getVideokey();
            Videokeydata videokeydata = videoRepository.findByvideokey(Postwatchkey);
            SourceDTO sourceDTO = new SourceDTO(
                    videokeydata.getVideokey(),
                    videokeydata.getYesResponse1(),
                    videokeydata.getNoResponse1(),
                    videokeydata.getTotal1()
            );

            return ResponseEntity.ok(sourceDTO);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(new SourceDTO());
        }
    }


    @Operation(summary = "videokey를 보내면 사용자들의 응답 전송", description = "영상에 따른 사실 의견 분리에 대한 사용자들의 응답을 전송합니다. ")
    @CrossOrigin(origins = "*")
    @PostMapping("/view-realityopinion")
    public ResponseEntity<OpinionDTO> opinionResponse(@RequestBody VideoRequest request) {

        try {
            String Postwatchkey = request.getVideokey();
            Videokeydata videokeydata = videoRepository.findByvideokey(Postwatchkey);
            OpinionDTO opinionDTO = new OpinionDTO(
                    videokeydata.getVideokey(),
                    videokeydata.getYesResponse2(),
                    videokeydata.getNoResponse2(),
                    videokeydata.getTotal2()
            );
            return ResponseEntity.ok(opinionDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OpinionDTO());
        }
    }





    //paging 된 데이터를 리턴하는 메소드
    @Operation(summary = "userId를 보내면 사용자들의 응답 전송", description = "영상에 따른 사용자들의 응답을 전송합니다. ")
    @CrossOrigin(origins = "*")
    @PostMapping("/viewpagingdata-byuserId")
    public ResponseEntity<Page<VideoDTO>> ReturnPagingData(
            @Parameter(description = "사용자 id 값" , required = true, example = "{ userId : 사용자 식별 아이디 }")
            @RequestBody UserIdRequest userIdRequest,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){


        try {
            Pageable pageable = PageRequest.of(page - 1, size);

            Page<Response> pagedData = responseRepository.findAllByUserId(userIdRequest.getUserId(), pageable);

            List<VideoDTO> videoDTOList = pagedData.stream()
                    .map(response -> new VideoDTO(
                            response.getId(),
                            response.getUserId(),
                            response.getVideokey(),
                            response.getYesResponse1(),
                            response.getNoResponse1(),
                            response.getYesResponse2(),
                            response.getNoResponse2(),
                            response.getGpt(),
                            response.getCategory(),
                            response.getPublisher(),
                            response.getPostDate()
                    )).collect(Collectors.toList());


            return ResponseEntity.ok().body(new PageImpl<>(videoDTOList, pageable, pagedData.getTotalElements()));
        }
        catch(Exception e){

            return ResponseEntity.badRequest().body(new PageImpl<>(Collections.emptyList()));
        }

    }


    @Operation(summary = "userId와 videokey를 보내면 해당하는 값을 전송", description = " userId와 videokey에 따른 사용자의 응답값, 영상 카테고리, 게시자, 게시일 , gpt의 응답 등을 리턴합니다. ")
    @CrossOrigin(origins = "*")
    @PostMapping("/viewby-userIdvideokey")
    public ResponseEntity<Response> ViewByuserIdVideoKey(
            @Parameter(description = "사용자 id 값 , 영상 key 값" , required = true, example = "{userId : 사용자 아이디 , videokey : 영상 key 값")
            @RequestBody UserIdViedokeyRequest request){
        try {
            String UserId = request.getUserId();
            String PostWatchkey = request.getVideokey();
            Response existingresponses = responseRepository.findByUserIdAndVideokey(UserId, PostWatchkey);

            return ResponseEntity.ok(existingresponses);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(new Response());
        }
    }

    @Operation(summary = "userId와 videokey를 보내면 해당하는 데이터를 삭제 ", description = " userId와 videokey에 따른 사용자의 응답값, 영상 카테고리, 게시자, 게시일 , gpt의 응답 등이 저장된 테이블의 열을 삭제합니다. 그리고 삭제된 것에 따라 영상 key에 따른 사용자들의 응답을 갱신합니다.")
    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete-responsedata")
    public String DeleteResponseData(@RequestBody UserIdViedokeyRequest request)
    {
        String PostWatchkey = request.getVideokey();
        String UserId = request.getUserId();
        Response existingresponses = responseRepository.findByUserIdAndVideokey(UserId, PostWatchkey);
        Videokeydata videokeydata = videoRepository.findByvideokey(PostWatchkey);

        if(existingresponses != null ){

            if(existingresponses.getYesResponse1() != 0){
                videokeydata.setYesResponse1(videokeydata.getYesResponse1()-1);

            }
            if(existingresponses.getNoResponse1() != 0){
                videokeydata.setNoResponse1(videokeydata.getNoResponse1()-1);

            }
            if(existingresponses.getYesResponse2() != 0){
                videokeydata.setYesResponse2(videokeydata.getYesResponse2()-1);

            }
            if(existingresponses.getNoResponse2() != 0){
                videokeydata.setNoResponse2(videokeydata.getNoResponse2()-1);

            }
            responseRepository.delete(existingresponses);


            return "delete completed";
        }
        else{
            return "delete failed";
        }

    }


    @Operation(summary = "userId와 videokey와 영상 자막을 보내면 GPT의 응답을 리턴합니다.", description = "GPT의 답변을 리턴하고 userId와 videokey가 일치하는 Response객체에 Gpt의 응답을 추가해서 수정해 저장합니다.")
    @Transactional
    @CrossOrigin(origins = "*")
    @PutMapping("/view-gptanswer")
    public ResponseEntity<JsonNode> GptResponseData(
            @Parameter(description = "userId, videokey , videosubtitle ", required = true ,example = "{userId : 사용자 id , videokey : 영상 key , videosubtitle : 영상자막 } ")
            @RequestBody GptRequest request){

        ProcessGPT processGPt = new ProcessGPT();
        String PostWatchkey = request.getVideokey();
        String UserId = request.getUserId();

        try {
            Response response = responseRepository.findByUserIdAndVideokey(UserId, PostWatchkey);

            if (response != null) {
                Response updatedResponse;
                String gptanswer = processGPt.PublisherGPT(request.getVideokey());
                System.out.println(gptanswer);
                Response response2 = response;

                response2.setGpt(gptanswer);
                updatedResponse = responseRepository.save(response2);

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode responseBody = objectMapper.createObjectNode().put("gpt", gptanswer);
                return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseBody);

            }else {

                String gptanswer = processGPt.PublisherGPT(request.getVideokey());
                System.out.println(gptanswer);

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode responseBody = objectMapper.createObjectNode().put("gpt", gptanswer);

                return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseBody);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @CrossOrigin("*")
    @PostMapping(value = "/answer-by-url", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object Return(@RequestBody UrlRequest urlRequest){

        ProcessGPT2 processGPT = new ProcessGPT2();

        try {
            Object news = processGPT.PublisherGPT(urlRequest.getUrl());
            return news;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Operation(summary = "게시자 값 전송", description = "게시자를 전송하면 언론사 목록 DB에 저장된 값인지 Y/N으로 응답합니다")
    @CrossOrigin(origins = "*")
    @PostMapping("/api/check-publisher")
    public String checkPublisher(
            @Parameter(description = "게시자 이름 값", required = true ,example = "{publisherValue : 게시자} ")
            @RequestBody PublisherRequest request) {
        String publisherName = request.getPublisherValue();
        try {
            // Mariadb에서 언론사 이름으로 조회
            Publisher publisher = publisherRepository.findByName(publisherName);

            // 언론사가 존재하는 경우 'Y'를 반환, 그렇지 않으면 'N'을 반환
            if (publisher != null) {
                return "Y";
            } else {
                return "N";
            }
        } catch (Exception e) {
            return "언론사 조회에 실패했습니다";
        }

    }



    // 프론트엔드에서 보낸 JSON을 매핑하기 위한 클래스
    public static class PublisherRequest {
        private String publisherValue;

        public String getPublisherValue() {
            return publisherValue;
        }

        public void setPublisherValue(String publisherValue) {
            this.publisherValue = publisherValue;
        }
    }























}
