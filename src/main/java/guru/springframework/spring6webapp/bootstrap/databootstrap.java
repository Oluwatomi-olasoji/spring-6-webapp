package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class databootstrap implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public databootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author tomi = new Author();
        tomi.setFirstName("Tomi");
        tomi.setLastName("Olasoji");

        Book mld = new Book();
        mld.setTitle("Motherland Diversity");
        mld.setIsbn("1234");

        Publisher okada = new Publisher();
        okada.setName("okada books");
        okada.setAddress("123 street, lekki phase 1, Lagos.");


        //Making a relationship between author and books, books and publisher
        tomi.getBooks().add(mld);
        mld.getAuthors().add(tomi);

        okada.getBooks().add(mld);
        mld.setPublisher(okada);

        //when you save an object in the repo, the repo returns a new  objects
        //its good practice to save the reference to the object
        Author tomiSavd = authorRepository.save(tomi);
        Publisher publisher = publisherRepository.save(okada);
        Book mldSaved = bookRepository.save(mld);


        Author chiamanda = new Author();
        chiamanda.setFirstName("Chiamanda");
        chiamanda.setLastName("Adechie");

        Book hoays = new Book();
        hoays.setTitle("Half of a yellow sun");
        hoays.setIsbn("4567");

        //relationship between author and books
        chiamanda.getBooks().add(hoays);
        tomi.getBooks().add(hoays);
        hoays.getAuthors().add(tomi);
        hoays.getAuthors().add(chiamanda);

        //relationship between book and publisher
        hoays.setPublisher(okada);
        okada.getBooks().add(hoays);

        Author chiamandaSavd = authorRepository.save(chiamanda);
        Publisher publisher1 = publisherRepository.save(okada);
        Book hoaysSaved = bookRepository.save(hoays);




    }
}
