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
@Table(name = "songs")
public class Song {

    @Id
    @Column(name = "song_id")
    private String id;

    private String name;

    @Column(name = "runtime")
    private String runTime;

    private String lyric;

    @Column(name = "file_link")
    private String fileLink;

    @ManyToMany
    @JoinTable (
            name = "albums_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private Set<Album> albums = new HashSet<>();
}
