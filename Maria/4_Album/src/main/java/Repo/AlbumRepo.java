package Repo;

import entity.Album;
import jakarta.persistence.TypedQuery;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AlbumRepo extends AbstractGenericRepo<AlbumRepo, String>{
    public boolean updatePriceOfAlbum(String id, double newPrice){
        return doInTransaction(em -> {
            Album album = em.find(Album.class,id);
            album.setPrice(newPrice);

            em.merge(album);
            return true;
        });
    }

    public List<Album> listAlbumByGenre(String genreName,int  year) {
        return doInTransaction(em -> {
            String jpql = """
                    select a
                    from Album a
                    where a.genre.name like :genreName and a.yearOfRelease =:year
                    """;
            TypedQuery<Album> query = em.createQuery(jpql, Album.class);
            query.setParameter("genreName",genreName);
            query.setParameter("year",year);
            return query.getResultList();
        });
    }

    public Map<String, Long> getNumberOfAlbumsByGenre() {
        return doInTransaction(em -> {
            String jpql = """
                    SELECT g.name, count(a)
                    from Genre g
                    join g.albums a
                    group by g.name
                    order by g.name asc
                    """;
            return em.createQuery(jpql,Object[].class).getResultList().stream().collect(
                    Collectors.toMap(
                            obj -> (String)obj[0],
                            obj -> (Long)obj[1]
                    )
            );
        });
    }
}
