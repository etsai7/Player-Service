package com.etsai.playerservice.controller;

import com.etsai.playerservice.dto.PlayerDTO;
import com.etsai.playerservice.dto.PlayerInfoDTO;
import com.etsai.playerservice.service.CSVUploadService;
import com.etsai.playerservice.service.PlayerService;
import com.etsai.playerservice.service.helper.CSVHelper;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController // Annotation for controller. Combo of @Controller and @ResponseBody
@Slf4j
@RequestMapping("data/") // Base A
public class PlayerController {

    private final PlayerService playerService;

    private final CSVUploadService csvUploadService;

    @Autowired
    private PlayerController(PlayerService playerService, CSVUploadService csvUploadService){
        this.playerService = playerService;
        this.csvUploadService = csvUploadService;
    }

    @PostMapping("create")
    public ResponseEntity<PlayerDTO> createDemoObjDto(@RequestHeader(value = "corrId", required = true) String corrId, @RequestBody PlayerDTO playerDTO){
        log.info("[{}] for Company: [{}]", corrId, playerDTO.getPlayerID());
        try{
            PlayerDTO savedObj = playerService.createPlayer(playerDTO);
            return new ResponseEntity<>(savedObj, HttpStatus.CREATED);
        } catch (DuplicateRequestException duplicate){
            return new ResponseEntity<>(playerDTO, HttpStatus.CONFLICT);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(playerDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<PlayerInfoDTO>> getAll(){
        return new ResponseEntity<>(csvUploadService.findAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("player/{playerID}")
    public ResponseEntity<PlayerInfoDTO> getCeoDto(@RequestHeader(value = "corrId", required = false) String corrId, @PathVariable("playerID") String playerID){
        PlayerInfoDTO player = playerService.getPlayerByPlayerId(playerID);
        if (player == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("csv/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file){
        String message = "";

        if (CSVHelper.checkIsCSVFormat(file)) {
            try {
                csvUploadService.saveCSV(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        return new ResponseEntity<>("File Uploaded!", HttpStatus.OK);
    }

//    @PostMapping("csv/upload-local")
//    public ResponseEntity<String> uploadCSV(){
//        MultipartFile file =
//
//        if (CSVHelper.checkIsCSVFormat(file)) {
//            try {
//                csvUploadService.saveCSV(file);
//
//                message = "Uploaded the file successfully: " + file.getOriginalFilename();
//                return ResponseEntity.status(HttpStatus.OK).body(message);
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
//            }
//        }
//
//        message = "Please upload a csv file!";
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
////        return new ResponseEntity<>("File Uploaded!", HttpStatus.OK);
//    }
}
