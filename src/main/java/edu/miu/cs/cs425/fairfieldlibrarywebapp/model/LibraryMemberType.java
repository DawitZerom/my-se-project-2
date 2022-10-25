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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LibraryMemberType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer libraryMemberTypeId;
    @NotBlank
    private String name;
    @NotBlank
    private Integer maxLengthBorrowBook;
    @NotBlank
    private Double overdueFee;
}
