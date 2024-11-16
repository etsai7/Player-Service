package com.etsai.playerservice.service;

import com.etsai.playerservice.dto.PlayerInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CSVUploadService {

    public void saveCSV(MultipartFile file);

    public List<PlayerInfoDTO> findAllPlayers();
}
