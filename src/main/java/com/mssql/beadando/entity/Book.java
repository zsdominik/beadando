package com.mssql.beadando.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @GenericGenerator(name = "seq", strategy = "increment")
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotNull(message = "Title cannot be null")
    private String title;

    @Column(nullable = false)
    @NotNull(message = "Publication date cannot be null")
    private LocalDateTime publicationDate;

    @Column(nullable = false, length = 1)
    @NotNull(message = "Edition date cannot be null")
    @Size(max = 1, message = "Edition cannot be more than 1 character")
    private Integer edition;

    private Integer availableQuantity;

    private Integer price;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private Author author;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private Publisher publisher;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<Genre> genre;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }
}
