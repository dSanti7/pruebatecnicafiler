import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Book;
import model.BookData;
import utils.Filter;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Filter filter = new Filter();
        //Leer fichero
        String fileData = readFile();
        //Mapeamos
        List<Book> bookList = getBooksFromJson(fileData);
        String filterName;
        if (args.length == 0) {
            filterName = "Harry";
        } else {
            filterName = args[0];
        }
        //Realizamos filtro
        Optional<BookData> books = filter.getResult(filterName, bookList);

        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("*****************Resultado*****************\n" +
                "************************************************************************\n" +
                "************************************************************************\n" +
                "************************************************************************\n"
                + books.get().getBookList());

    }

    private static List<Book> getBooksFromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Book> bookList;
        try {
            bookList = objectMapper.readValue(json, new TypeReference<List<Book>>() {
            });


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    private static String readFile() {
        String rutaArchivo = "resources/books.json";
        File archivo = new File(rutaArchivo);
        StringBuilder linea = new StringBuilder();
        try {
            Scanner scanner = new Scanner(archivo);
            while (scanner.hasNextLine()) {
                linea.append(scanner.nextLine());
            }
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return linea.toString();
    }
}