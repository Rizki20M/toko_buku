package com.example.tokobuku.repo;
import com.example.tokobuku.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
