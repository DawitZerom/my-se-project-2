package edu.miu.cs.cs425.fairfieldlibrarywebapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;
    @NotBlank(message = "Author cannot be blank")
    private String author;
}
