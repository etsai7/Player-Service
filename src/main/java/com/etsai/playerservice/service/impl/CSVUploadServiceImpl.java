package com.etsai.playerservice.service.impl;

import com.etsai.playerservice.dto.PlayerInfoDTO;
import com.etsai.playerservice.entity.PlayerEntity;
import com.etsai.playerservice.mapper.PlayerMapper;
import com.etsai.playerservice.repository.IntuitDbRepository;
import com.etsai.playerservice.service.CSVUploadService;
import com.etsai.playerservice.service.helper.CSVHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CSVUploadServiceImpl implements CSVUploadService {

    @Autowired
    IntuitDbRepository intuitDbRepository;

    @Autowired
    PlayerMapper mapper;

    @Override
    public void saveCSV(MultipartFile file) {
        log.info("Saving CSV fild: [{}]", file.getName());

        log.info("About to convert csvs to Demo Objs");
        try{
            List<PlayerEntity> objs = CSVHelper.csvToDemoObjs(file.getInputStream());
            log.info("Finished converted to list of demo Objs");
            intuitDbRepository.saveAll(objs);
        } catch(IOException e){
            log.error("Failed to convert from csv to objects");
            throw new RuntimeException("failed to store csv data: " + e.getMessage());
        }
    }

    /**
     * @return
     */
    @Override
    public List<PlayerInfoDTO> findAllPlayers() {
       return intuitDbRepository.findAll().stream().map(mapper::mapToPlayerInfoDTO)
                .collect(Collectors.toList());
    }
}
