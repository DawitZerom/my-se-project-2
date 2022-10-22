
package edu.miu.cs.cs425.fairfieldlibrarywebapp.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CheckoutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkoutRecordId;
    @Temporal(TemporalType.DATE)
    private LocalDate checkoutDate;
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;
    private boolean isCheckedIn;
    @Temporal(TemporalType.DATE)
    private LocalDate checkinDate;
    private double overdueFee;

    @OneToOne
    private Book book;
    @OneToOne
    private LibraryMember member;

    private static final int BORROW_MAX_LENGTH = 7;

    public CheckoutRecord(Book book, LibraryMember member) {
        LocalDate today = LocalDate.now();
        this.checkoutDate = today;

        LocalDate due = today.plusDays(BORROW_MAX_LENGTH);
        this.dueDate = due;
        this.book = book;
        this.member = member;
        this.isCheckedIn = false;
    }

}
