package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @Column(name = "album_id")
    private String id;

    private String title;

    private double price;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @Column(name = "download_link")
    private String downloadLink;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany(mappedBy = "albums")
    private Set<Song> songs = new HashSet<>();

}
