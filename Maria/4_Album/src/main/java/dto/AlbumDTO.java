package dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AlbumDTO implements Serializable {

    private String albumId;

    private String title;

    private double price;

    private int yearOfRelease;

    private String downloadLink;

    private String genreName;

}
