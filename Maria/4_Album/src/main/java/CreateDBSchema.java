import jakarta.persistence.Persistence;
import service.AlbumService;

public class CreateDBSchema {
    public static void main(String[] args) {
//        Persistence.createEntityManagerFactory("mariadb-pu").createEntityManager();

        AlbumService albumService = new AlbumService();

//        boolean res = albumService.updatePriceOfAlbum("ALB010",12);
//        System.out.println(res?"update success":"update error");

//        albumService.listAlbumByGenre("Popular music",1966).forEach(System.out::println);

        albumService.getNumberOfAlbumsByGenre().forEach((k,v)->{
            System.out.println(k+ " "+ v);
        });
    }
}
