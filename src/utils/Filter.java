package utils;

import model.Book;
import model.BookData;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Filter {

    public Optional<BookData> getResult(String filter, List<Book> books) {
        return filter(filter, books);
    }

    private Optional<BookData> filter(String filter, List<Book> books) {

        //Escriba por pantalla los libros que no tengan fecha de publicación
        List<Book> withoutPublicationTimestamp = books.stream().filter(
                        book -> Optional.ofNullable(book.getPublicationTimestamp()).orElse(0L) == 0)
                .collect(Collectors.toList());
        List<Book> withPublicationTimestamp = books.stream().filter(
                        book -> Optional.ofNullable(book.getPublicationTimestamp()).orElse(0L) != 0)
                .collect(Collectors.toList());

        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");
        System.out.println("*****************Libros sin fecha de publicación *****************\n" +
                "************************************************************************\n" +
                "************************************************************************\n" +
                "************************************************************************\n" + withoutPublicationTimestamp);

        List<Book> booksListWithFilter = withPublicationTimestamp.stream().filter(book ->
                        //Devuelva los libros que contengan la cadena de caracteres en el nombre, en el
                        //resumen y en la biografia del autor del libro.
                        book.getTitle().contains(filter) &&
                                book.getSummary().contains(filter) &&
                                book.getAuthor().getBio().contains(filter))
                .collect(Collectors.toList());

        //En caso de encontrar más de un
        //libro en la lista devolver aquel más recientemente publicado
        if (booksListWithFilter.size() > 1) {

            booksListWithFilter = booksListWithFilter.stream().sorted(
                    Comparator.comparing(Book::getPublicationTimestamp)
            ).collect(Collectors.toList());


        }
        //Además se deberá
        //devolver el libro con un campo date adicional que contenga el timestamp con el
        //siguiente formato de fecha: mm-dd-yyyy.

        booksListWithFilter = booksListWithFilter.stream()
                .peek(book -> book.setDate(convertTimeStampToDate(book.getPublicationTimestamp())))
                .sorted(
                        //agrupada por fecha de publicación
                        Comparator.comparingLong(Book::getPublicationTimestamp).reversed()
                                //y luego ordenada por la biografia de autor más corta
                                .thenComparingInt(book -> book.getAuthor().getBio().length())
                ).collect(Collectors.toList());


        return Optional.of(new BookData(booksListWithFilter));
    }

    private String convertTimeStampToDate(Long publicationTimestamp) {
        Date date = new Date(publicationTimestamp * 1000);

        // Crear un objeto LocalDateTime a partir del Timestamp
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        ;

        // Formatear la fecha usando DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return localDate.format(formatter);

    }


}
