
Insert into artists (artist_id, name, birth_date, url) values ('ADE', 'Adele', '1988-05-05', 'https://www.adele.com/');
Insert into artists (artist_id, name, birth_date, url) values ('DOR', 'Doroteo Arnaiz', '1997-08-15', 'https://www.facebook.com/doroteoarnaiz');
Insert into artists (artist_id, name, birth_date, url) values ('BIL', 'Bill Arnold', '1990-01-01', 'https://www.facebook.com/billarnold');
Insert into artists (artist_id, name, birth_date, url) values ('CHA', 'Charles Arnoldi', '1946-04-25', 'https://www.charlesarnoldi.com');
Insert into artists (artist_id, name, birth_date, url) values ('PER', 'Per Arnoldi', '1941-12-25', 'https://www.perarnoldi.com');
Insert into artists (artist_id, name, birth_date, url) values ('BIA', 'Bill Aron', '1941-01-01', 'https://www.billaron.com');
Insert into artists (artist_id, name, birth_date, url) values ('DAV', 'David Aronson', '1923-10-28', 'https://www.davidaronson.com');
Insert into artists (artist_id, name, birth_date, url) values ('IRE', 'Irene Aronson', '1918-08-03', 'https://www.irenearonson.com');
Insert into artists (artist_id, name, birth_date, url) values ('JEAN', 'Jean (Hans) Arp', '1886-09-16', 'https://www.jeanarp.com');
Insert into artists (artist_id, name, birth_date, url) values ('JUR', 'Juri Arrak', '1936-10-02', 'https://www.juriarrak.com');


INSERT INTO genres (genre_id, description, name) VALUES
('POP', 'Popular music is music with wide appeal that is typically distributed to large audiences through the music industry. These forms and styles can be enjoyed and performed by people with little or no musical training.', 'Popular music'),
('JAZZ', 'Jazz is a music genre that originated in the African-American communities of New Orleans, United States, in the late 19th and early 20th centuries, with its roots in blues and ragtime.', 'Jazz'),
('COUNTRY', 'Country music, also known as country and western, and hillbilly music, is a genre of popular music that takes its roots from genres such as blues and old-time music.', 'Country music'),
('ROCK', 'Rock music is a broad genre of popular music that originated as "rock and roll" in the United States in the late 1940s and early 1950s, developing into a range of different styles in the mid-1960s and later.', 'Rock'),
('CLASSIC', 'Classic music is a broad term that usually refers to music that is created to be art and is often played by professional musicians using traditional instruments.', 'Classic music'),
('BLUES', 'Blues is a music genre and musical form which was originated in the Deep South of the United States around the 1860s by African-Americans.', 'Blues'),
('HIPHOP', 'Hip hop music, also known as rap music, is a genre of popular music developed in the United States in the Bronx in the 1970s.', 'Hip hop'),
('ELECTRONIC', 'Electronic music is music that employs electronic musical instruments, digital instruments, or circuitry-based music technology in its creation.', 'Electronic music'),
('REGGAE', 'Reggae is a music genre that originated in Jamaica in the late 1960s.', 'Reggae'),
('LATIN', 'Latin music is a genre used by the music industry for music from Spanish- and Portuguese-speaking regions.', 'Latin music');



INSERT INTO songs (song_id, file_link, lyric, name, runtime) VALUES
('SOG1', 'http://www.youtube.com/watch?v=3ZlDZPYzfm4', 'I am fueled by all forms of failure, I paid the price so I will take what is mine, Betrayed despaired I am stung by rejection...', 'This Is the House That Doubt Built', 3.5),
('SOG2', 'http://www.youtube.com/watch?v=3ZlDZPYzfm4', 'She sits home and dreams of a way to be, and I am telling myself that she would rather be with anyone else...', 'Sticks & Bricks', 3.3),
('SOG3', 'http://www.youtube.com/watch?v=3ZlDZPYzfm4', 'I am sorry, if I seem uninterested, or I am not listening or I am indifferent...', 'All I Want', 3.4),
('SOG4', 'http://www.youtube.com/watch?v=834MYdsad4', 'You cannot decide if I am the living truth or the dying breath, No one waits for you...', 'It is Complicated', 3.0),
('SOG5', 'http://www.youtube.com/watch?v=834MYdsad4', 'I have killed a million petty souls, but I could not kill...', '2nd Sucks', 2.5),
('SOG6', 'http://www.youtube.com/watch?v=834MYdsad4', 'I am not a poet, I am a mess, I am a failure...', 'Better Off This Way', 3.5),
('SOG7', 'http://www.youtube.com/watch?v=834MYdsad4', 'I hate this town, it is so washed up...', 'All Signs Point to Lauderdale', 3.3),
('SOG8', 'http://www.youtube.com/watch?v=834MYdsad4', 'I am fading fast, not going to last...', 'Out of Time', 3.5),
('SOG9', 'http://www.youtube.com/watch?v=834MYdsad4', 'I am a mess, that is the best way to describe it...', 'If I Leave', 3.5),
('SOG10', 'http://www.youtube.com/watch?v=834MYdsad4', 'Hey, slow it down, whataya want from me...', 'Whataya Want From Me', 3.8);



INSERT INTO albums (album_id, download_link, price, title, year_of_release, genre_id) VALUES
('ALB001', 'https://www.songs/sgt-peppers-lonely-hearts-club-band', 10.5, 'Sgt. Pepper''s Lonely Hearts Club Band', 1967, 'BLUES'),
('ALB002', 'https://www.songs/pet-sounds', 10.5, 'Pet Sounds', 1966, 'POP'),
('ALB003', 'https://www.songs/revolver', 10.5, 'Revolver', 1966, 'ROCK'),
('ALB004', 'https://www.songs/highway-61-revisited', 10.5, 'Highway 61 Revisited', 1965, 'POP'),
('ALB005', 'https://www.songs/rubber-soul', 10.5, 'Rubber Soul', 1965, 'BLUES'),
('ALB006', 'https://www.songs/whats-going-on', 10.5, 'What''s Going On', 1971, 'CLASSIC'),
('ALB007', 'https://www.songs/exile-on-main-st', 10.5, 'Exile on Main St.', 1972, 'COUNTRY'),
('ALB008', 'https://www.songs/london-calling', 10.5, 'London Calling', 1979, 'COUNTRY'),
('ALB009', 'https://www.songs/blonde-on-blonde', 10.5, 'Blonde on Blonde', 1966, 'CLASSIC'),
('ALB010', 'https://www.songs/the-white-album', 10.5, 'The White Album', 1968, 'POP');



-- Albums and Artists
INSERT INTO albums_artists (album_id, artist_id) VALUES
('ALB001', 'ADE'), ('ALB001', 'DAV'), ('ALB002', 'JUR'), ('ALB002', 'DOR'),
('ALB003', 'JEAN'), ('ALB004', 'CHA'), ('ALB003', 'BIA'), ('ALB004', 'JEAN'),
('ALB005', 'IRE'), ('ALB005', 'PER'), ('ALB006', 'BIL'), ('ALB007', 'DAV'),
('ALB008', 'IRE'), ('ALB009', 'JEAN'), ('ALB010', 'JUR'), ('ALB010', 'JEAN'),
('ALB009', 'IRE');

-- Albums and Songs
INSERT INTO albums_songs (album_id, song_id) VALUES
('ALB001', 'SOG1'), ('ALB001', 'SOG2'), ('ALB002', 'SOG3'), ('ALB002', 'SOG4'),
('ALB003', 'SOG5'), ('ALB003', 'SOG6'), ('ALB004', 'SOG7'), ('ALB004', 'SOG8'),
('ALB005', 'SOG9'), ('ALB005', 'SOG10'), ('ALB006', 'SOG1'), ('ALB006', 'SOG2'),
('ALB007', 'SOG3'), ('ALB007', 'SOG4'), ('ALB007', 'SOG5'), ('ALB006', 'SOG6'),
('ALB005', 'SOG7');
