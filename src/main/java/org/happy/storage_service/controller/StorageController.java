package org.happy.storage_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.happy.storage_service.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

@Slf4j
@RestController
//@RequestMapping("/image")
public class StorageController {

    @Autowired
    private StorageService storageService;

//    @GetMapping("/")
//    public HttpEntity<?> index() {
//        return HttpEntity<>
//    }

    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        String deleteImage = storageService.deleteImage(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(deleteImage);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image,
                                         @RequestParam("filename") String name) throws IOException {
        String uploadImage = storageService.uploadImage(image, name);
//        String uploadImage = "abhishek";
        log.info(name);

        return ResponseEntity.ok(uploadImage);
    }

    @GetMapping("/images")
    public ResponseEntity<?> fetchAllImage() {
        List<Map<String, ? extends Serializable>> images = storageService.fetchImage();
        return ResponseEntity.ok(images);
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable("id") Long id) throws DataFormatException {
        byte[] imageDate = storageService.downloadImage(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageDate);
    }
}
