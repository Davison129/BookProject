package demo.controller;


import demo.exception.BookNotFoundException;
import demo.model.Book;
import demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Book> listBooks = bookRepository.findAll();
        model.addAttribute("listBooks", listBooks);
        return "welcome";
    }

    // Delete a Note
    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(value = "id") Long bookId, Model model) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        bookRepository.delete(book);

        return viewHomePage(model);

    }
    // Create a new Note
    @RequestMapping("/new")
    public String createBook() {
        return "bookform";
    }

    // Create a new Note
    @PostMapping("/books")
    public String createNote(@ModelAttribute("book")  Book book, Model model) {
        bookRepository.save(book);
        return viewHomePage(model);
    }

    // Update a Note
    @RequestMapping(value = "books/save", method = RequestMethod.POST)
    public String updateNote( @ModelAttribute("book")  Book book, Model model) throws BookNotFoundException {
        /*Book oldBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        oldBook.setBook_name(book.getBook_name());
        oldBook.setAuthor_name(book.getAuthor_name());
        oldBook.setIsbn(book.getIsbn());*/

        bookRepository.save(book);

        return viewHomePage(model);
    }

    // Get a Single Note
    @GetMapping("/books/{id}")
    public String getNoteById(@PathVariable(value = "id") Long bookId, Model model) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        model.addAttribute("book", book);
        return "editform";
    }

}