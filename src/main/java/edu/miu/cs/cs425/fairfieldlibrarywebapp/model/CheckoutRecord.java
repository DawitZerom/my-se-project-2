
package edu.miu.cs.cs425.fairfieldlibrarywebapp.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class CheckoutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkoutRecordId;

    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private String isCheckedIn;
    private LocalDate checkinDate;
    private double overdueFee;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne
    @JoinColumn(name = "library_member_id")
    private LibraryMember libraryMember;

    private static final int BORROW_MAX_LENGTH = 7;

    public CheckoutRecord(Book book, LibraryMember libraryMember) {
        LocalDate today = LocalDate.now();
        this.checkoutDate = today;

        LocalDate due = today.plusDays(BORROW_MAX_LENGTH);
        this.dueDate = due;
        this.book = book;
        this.libraryMember = libraryMember;
        this.isCheckedIn = "No";
    }
}
