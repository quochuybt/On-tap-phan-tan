import dto.AlbumDTO;
import network.CommandType;
import network.Request;
import network.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket(InetAddress.getLocalHost().getHostName(),9090);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)
        ){
            int choice = 0;

            while (true) {
                System.out.println("Menu");
                System.out.println("1. Update price of album");
                System.out.println("2. List album by genre");
                System.out.println("3. Get number of album by genre");

                choice = scanner.nextInt();
                scanner.nextLine();
                Request request = new Request();

                switch (choice) {
                    case 1 -> {
                        System.out.println("nhap Album id:");
                        String albumId = scanner.nextLine();
                        System.out.println("nhap gia moi:");
                        long newPrice = scanner.nextLong();
                        scanner.nextLine();

                        AlbumDTO albumDTO = AlbumDTO.builder()
                                .albumId(albumId)
                                .price(newPrice)
                                .build();

                        request.setObject(albumDTO);
                        request.setCommandType(CommandType.UPDATE_PRICE_OF_ALBUM);
                    }
                    case 2 -> {
                        System.out.println("Nhap ten the loai:");
                        String genreName = scanner.nextLine();
                        System.out.println("Nhap nam:");
                        int year = scanner.nextInt();
                        scanner.nextLine();

                        AlbumDTO albumDTO = AlbumDTO.builder()
                                .genreName(genreName)
                                .yearOfRelease(year)
                                .build();

                        request.setObject(albumDTO);
                        request.setCommandType(CommandType.LIST_ALBUM_BY_GENRE);
                    }
                    case 3 -> {
                        request.setCommandType(CommandType.GET_NUMBER_OF_ALBUM_BY_GENRE);
                    }
                }
                out.writeObject(request);
                out.flush();

                Response response = (Response) in.readObject();
                System.out.println(response);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
