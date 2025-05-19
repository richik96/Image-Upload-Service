package org.happy.storage_service.service;


import jdk.dynalink.linker.LinkerServices;
import org.happy.storage_service.exception.FileNotUploaded;
import org.happy.storage_service.enitity.ImageData;
import org.happy.storage_service.repository.StorageRepository;
import org.happy.storage_service.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public String uploadImage(MultipartFile file, String name) throws IOException {

        System.out.println("in Service layer");
        ImageData imageData = storageRepository.save(ImageData.builder()
                .name(name)
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());

        System.out.println("After imagedata creation");

        if(imageData != null) {
            return "file uploaded successfully! "+file.getOriginalFilename();
        }
        throw new FileNotUploaded("File not getting processed");
    }

    public byte[] downloadImage(Long id) throws DataFormatException {
        Optional<ImageData> dbImageData = storageRepository.findById(id);

        byte[] image = ImageUtils.decompressImage(dbImageData.get().getImageData());

        return image;
    }

    public List<Map<String,? extends Serializable>> fetchImage() {
            return storageRepository.findAll()
                    .stream()
                    .map(img -> Map.of(
                            "id", img.getId(),
                            "file_name", img.getName(),
                            "content-type", img.getType()
                    ))
                    .collect(Collectors.toUnmodifiableList());
    }

    public String deleteImage(Long id) {

        if(storageRepository.existsById(id)) {
            storageRepository.deleteById(id);
            return "Image has been deleted.";
        }
        return "Image not found";
    }
}
