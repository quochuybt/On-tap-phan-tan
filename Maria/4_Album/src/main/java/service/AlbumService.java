package service;

import Repo.AlbumRepo;
import dto.AlbumDTO;

import java.util.List;
import java.util.Map;

public class AlbumService {
    private AlbumRepo albumRepo;

    public AlbumService() {
        this.albumRepo = new AlbumRepo();
    }

    public boolean updatePriceOfAlbum(String id, double newPrice){
        return albumRepo.updatePriceOfAlbum(id,newPrice);
    }

    public List<AlbumDTO> listAlbumByGenre(String genreName,int  year){
        return albumRepo.listAlbumByGenre(genreName,year).stream().map(
                obj -> AlbumDTO.builder()
                        .title(obj.getTitle())
                        .price(obj.getPrice())
                        .albumId(obj.getId())
                        .downloadLink(obj.getDownloadLink())
                        .yearOfRelease(obj.getYearOfRelease())
                        .genreName(obj.getGenre().getName())
                        .build()
        ).toList();
    }

    public Map<String, Long> getNumberOfAlbumsByGenre() {
        return albumRepo.getNumberOfAlbumsByGenre();
    }
}
