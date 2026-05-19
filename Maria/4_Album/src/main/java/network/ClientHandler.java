package network;


import dto.AlbumDTO;
import service.AlbumService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ClientHandler implements Runnable{
    private Socket socket;
    private AlbumService albumService;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.albumService = new AlbumService();
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ) {
            while (true) {

                Request request = (Request) in.readObject();
                CommandType commandType = request.getCommandType();
                Response response = new Response();

                switch (commandType) {
                    case UPDATE_PRICE_OF_ALBUM -> {
                        AlbumDTO albumDTO =(AlbumDTO) request.getObject();
                        boolean res = albumService.updatePriceOfAlbum(albumDTO.getAlbumId(), albumDTO.getPrice());
                        response.setSuccess(res);
                        response.setMessage(res?"update success":"update error");
                        response.setData(albumDTO);
                    }
                    case LIST_ALBUM_BY_GENRE -> {
                        AlbumDTO albumDTO =(AlbumDTO) request.getObject();
                        List<AlbumDTO> list = albumService.listAlbumByGenre(albumDTO.getGenreName(),albumDTO.getYearOfRelease());
                        response.setSuccess(true);
                        response.setData(list);
                        response.setMessage("get success");

                    }
                    case GET_NUMBER_OF_ALBUM_BY_GENRE -> {
                        Map<String, Long> res = albumService.getNumberOfAlbumsByGenre();
                        response.setSuccess(true);
                        response.setData(res);
                        response.setMessage("get success");
                    }
                }

                out.writeObject(response);
                out.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
