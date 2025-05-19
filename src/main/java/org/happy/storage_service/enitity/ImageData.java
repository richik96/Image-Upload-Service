package org.happy.storage_service.enitity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image_data")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String type;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;

}
