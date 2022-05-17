package pack;

import java.util.*;

import java.time.*;
import java.time.temporal.*;

public class Librarian {
    Scanner librarian = new Scanner(System.in);
    Scanner librarianInt = new Scanner(System.in);
    int startOption, menuOption, loginOption, categoryChoice;
    String adminName, adminPassword;
    static String userName, userPassword, userId;
    String adminName1 = "tringer";
    String adminPassword1 = "tringer";
    static List<userDetail> userdetail = new LinkedList<userDetail>(); // create a static collection for userdetail
    static List<Category> category = new LinkedList<Category>(); // create a static collection for category
    static List<BooksDetail> bookDetail = new LinkedList<BooksDetail>(); // create a static collection for
                                                                         // bookdetail
    static List<DueDate> bookdueDate = new LinkedList<DueDate>();

    int rating = 1;
    int bookQuantity, initialQuantity;
    int quantity = 0;
    int totalQuantity = 0;
    long newdifference;
    float daysBetween = 0;
    String bookTitle = " ";
    String dueDate = " ";
    String currentDate = " ";
    String returnStatus = "No";
    // method for getting option in the start

    public void input() {
        System.out.println("**TRINGERS LIBRARY**");
        System.out.println("***************************");
        System.out.println("1. Librarian");
        System.out.println("2. User");
        System.out.println("3. Exit");
        System.out.println("\n Enter Your Option:");
        startOption = librarianInt.nextInt();
        while (true) {
            switch (startOption) {
                case 1:
                    librarianLogin();// call the method librarianLogin()
                    break;
                case 2:
                    User us = new User();// create object for User class in User file
                    us.userLogin();// call the method userLogin() in User class in User file
                    return;
                case 3:
                    System.exit(0);
            }
        }

    }

    // creating librarianLogin() method for the admin login
    void librarianLogin() {
        System.out.println("**************************");
        System.out.println("1.enter for login\n2.exit");
        System.out.println("\n enter your option");
        loginOption = librarianInt.nextInt();
        while (true) {
            switch (loginOption) {
                case 1:
                    System.out.println("**************************");
                    System.out.println("enter admin name:");
                    adminName = librarian.nextLine();// getting input for adminname from library admin
                    System.out.println("enter admin password");
                    adminPassword = librarian.nextLine();// getting input for adminpassword for library admin
                    if (adminName.equals(adminName1) && adminPassword.equals(adminPassword1))// compare the getting
                                                                                             // inputs and standard
                                                                                             // value
                    {
                        librarianOption();// if the condition returns true the librarianOption() method will be called
                    } else {
                        System.out.println("please give correct adminname and password");// if the condition return
                                                                                         // false
                                                                                         // the statement will be
                                                                                         // printed
                    }
                    return;
                case 2:
                    input();
            }
        }
    }

    // getting option in the menu
    public void librarianOption() {
        System.out.println("**************************");
        System.out.println("1. Add Category");
        System.out.println("2. Add books");
        System.out.println("3. Edit Quantities");
        System.out.println("4. Delete Books");
        System.out.println("5. Set Penalty");
        System.out.println("6.Add User");
        System.out.println("7. Show report of defaulter list");
        System.out.println("8. Show report of books");
        System.out.println("9. update penalty");
        System.out.println("10.update date");
        System.out.println("11.logout");
        System.out.println("\n Enter your option:");
        menuOption = librarianInt.nextInt();
        while (true) {
            switch (menuOption) {
                case 1:
                    addCategory();
                    librarianOption();
                    break;
                case 2:
                    displayCategory();
                    librarianOption();
                    break;
                case 3:
                    editQuantity();
                    librarianOption();
                    break;
                case 4:
                    deleteBooks();
                    librarianOption();
                    break;
                case 5:
                    setPenalty();
                    librarianOption();
                    break;
                case 6:
                    addUser();
                    librarianOption();
                    return;
                case 7:
                    reportDefaultList();
                    librarianOption();
                    break;
                case 8:
                    reportBook();
                    librarianOption();
                    break;
                case 9:
                    updatePenalty();
                    ;
                    librarianOption();
                    break;
                case 10:
                    updateDate();
                    librarianOption();
                    break;
                case 11:
                    input();// call the method input()
                    break;
            }
        }
    }

    // creating the method addUser() for adding user
    public void addUser() {
        System.out.println("**************************");
        System.out.print("Set The User Name:");
        userName = librarian.nextLine();

        System.out.print("Set The User Password:");
        userPassword = librarian.nextLine();

        System.out.print("Set The User id:");
        userId = librarian.nextLine();
        boolean found = false;
        Iterator<userDetail> userDetailItr = userdetail.iterator();
        while (userDetailItr.hasNext()) {
            userDetail userdDetail = userDetailItr.next();
            if (userdDetail.getUserId().contains(userId))// check the user id is already contains
            {
                System.out.println("Give unique userId");// if true, print the statement
                found = true;
            }
        }
        if (!found) {

            userdetail.add(new userDetail(userName, userPassword, userId));// pass the paramters to userDetail
                                                                           // Constructor and add to the collection

            // System.out.println(userdetail);
        }
    }

    // creating the method for adding category
    public void addCategory() {
        System.out.println("**************************");
        System.out.print("how many category you want to create:");
        int categoryCount = librarianInt.nextInt();
        for (int i = 0; i < categoryCount; i++) {
            System.out.print("Set The category name:");
            String categoryName = librarian.nextLine();
            category.add(new Category(categoryName));
        }

    }

    // creating the method for display the category in the add books
    public void displayCategory() {
        System.out.println("**************************");
        System.out.print("choose category");
        for (int i = 0; i < category.size(); i++) {
            System.out.println("\n" + i + " " + category.get(i));
        }
        addBooks();
    }

    // creating the method for adding books to the category
    public void addBooks() {

        Object categoryName = " ";
        System.out.println("\nenter the option");
        categoryChoice = librarianInt.nextInt();
        boolean found = false;
        for (int i = 0; i < category.size(); i++) {
            if (i == categoryChoice) {
                categoryName = category.get(i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("category not found");
        }
        if (found == true) {
            System.out.println("**************************");
            System.out.print("Set The Book Title:");
            String bookTitle = librarian.nextLine();

            System.out.print("Set The Book Author:");
            String bookAuthor = librarian.nextLine();

            System.out.print("Set The Book Quantity:");
            bookQuantity = librarianInt.nextInt();
            quantity = 0;
            totalQuantity = bookQuantity;
            quantity += totalQuantity;
            bookDetail.add(new BooksDetail(categoryName, bookTitle, bookAuthor, rating, bookQuantity, totalQuantity,
                    quantity));// pass
            // the
            // paramters
            // to
            // Booksdetail class

            // System.out.println(bookDetail);
        }
    }

    // edit the total quantity of book
    public void editQuantity() {
        int count = 0;
        int count2 = 0;
        boolean categoryFound = false;
        boolean bookFound = false;
        System.out.println("enter the category name you want to edit");
        String rentCategory = librarian.nextLine();
        System.out.println("***************************************");
        String categorysName = category.toString();

        if (categorysName.contains(rentCategory)) {
            categoryFound = true;
            for (BooksDetail books : bookDetail) {

                if (books.getCategory().toString().equals(rentCategory)) {
                    System.out.println(books);// if true , print the statement
                }

            }
            System.out.println("***********************************************");
            System.out.println("enter the book title you want to edit");
            String searchbookTitle = librarian.nextLine();

            for (BooksDetail books : bookDetail) {
                String bookTitle = books.getBookTitle();
                if (books.getBookTitle().equals(searchbookTitle)) {
                    bookFound = true;
                    System.err.println("\nAre you want to edit the quantity of book?\n Yes or No");
                    String editChoice = librarian.nextLine();

                    if (editChoice.equals("Yes")) {
                        System.out.println("\nenter the quantity of book you want to edit");
                        int bookQuantity = librarianInt.nextInt();

                        int Quantity = books.getBookQuantity();
                        initialQuantity = books.getBookInitialQuantity();
                        Object categoryName = books.getCategory();
                        String bookAuthor = books.getBookAuthor();
                        int newTotalQuantity = books.getBooktotalQuantity();
                        newTotalQuantity += bookQuantity;
                        Quantity += bookQuantity;
                        Iterator<BooksDetail> rentItr = bookDetail.iterator();// create object for iterator
                        while (rentItr.hasNext())// check the cursor point before the element
                        {
                            BooksDetail rentBooks = rentItr.next();// move the cursor to the next element
                            count2++;
                            // check the user given inputs are matched to the bookdetails set by the admin
                            if (rentBooks.getBookTitle().equals(searchbookTitle)) {
                                count += count2;
                            }
                        }
                        count -= 1;
                        // System.out.println(count);

                        bookDetail.set(count, new BooksDetail(categoryName, bookTitle, bookAuthor, rating,
                                initialQuantity, newTotalQuantity, Quantity));
                    } else {
                        librarianOption();
                    }

                }

            }
        }
        if (!bookFound) {
            System.out.println("Book Not Found");
        }
        if (!categoryFound) {
            System.out.println("Category Not Found");
        }
    }

    // delete the book if all the rented books returned
    public void deleteBooks() {
        int count = 0;
        int count2 = 0;
        boolean categoryFound = false;
        boolean bookFound = false;
        boolean quantityfound = false;
        String deleteBook = " ";
        System.out.println("enter the category name you want");
        String deleteCategory = librarian.nextLine();

        String categorysName = category.toString();
        System.out.println(categorysName);
        if (categorysName.contains(deleteCategory)) {
            categoryFound = true;
            for (BooksDetail books : bookDetail) {

                if (books.getCategory().toString().equals(deleteCategory)) {
                    System.out.println(books);// if true , print the statement
                }

            }
            System.out.println("enter the book title you want to delete");
            deleteBook = librarian.nextLine();
            for (BooksDetail books : bookDetail) {
                if (books.getBookTitle().equals(deleteBook)) {
                    bookFound = true;
                    Iterator<BooksDetail> deleteItr = bookDetail.iterator();// create object for iterator
                    while (deleteItr.hasNext())// check the cursor point before the element
                    {
                        BooksDetail deleteBooks = deleteItr.next();// move the cursor to the next element
                        count2++;
                        // check the user given inputs are matched to the bookdetails set by the admin
                        if (deleteBooks.getBookTitle().equals(deleteBook)
                                && deleteBooks.getBooktotalQuantity() == deleteBooks.getBookQuantity()) {
                            count += count2;
                            quantityfound = true;
                            // System.out.println(deleteBooks.getBookInitialQuantity());
                            // System.out.println(deleteBooks.getBookQuantity());
                        }
                    }
                    count -= 1;
                    // System.out.println(books.getBookInitialQuantity());
                    // System.out.println(books.getBookQuantity());
                }
            }
        }
        if (quantityfound == true) {
            System.out.println(count);
            bookDetail.remove(count);
            for (DueDate bookname : bookdueDate) {
                if (bookname.getbookTitle().equals(deleteBook)) {
                    bookdueDate.remove(count);

                }
            }
        }
        if (!quantityfound) {
            System.out.println("some book is not returned");
        }

        if (!bookFound) {
            System.out.println("Book Not Found");
        }
        if (!categoryFound) {
            System.out.println("Category Not Found");
        }
    }

    // penalty for the not returned rented books
    public void setPenalty() {
        int firstDay = 1;
        int secondDay = 5;
        int thirdDay = 10;
        int fourthDay = 50;

        System.out.println("Penalty For Books Not Returned On Time");
        System.out.println("\n1.For First Day-Rs" + firstDay + "\n2.For Second Day-Rs" + secondDay
                + "\n3.For Third Day-Rs" + thirdDay + "\n4.From Fourth Day-Rs" + fourthDay);
    }

    // update the date for penalty calculation
    public void updateDate() {

        long difference = 0;
        int count = 0;
        int count2 = 0;
        int penalty;
        String duedate = " ";
        String currentDate = " ";
        LocalDate updatedate = LocalDate.now();

        for (DueDate currentdate : bookdueDate) {
            currentDate = currentdate.getcurrentDate();
        }
        System.out.println(currentDate);

        System.out.println("Are you want to update the current date:Yes/No");
        String updateOption = librarian.nextLine();

        if (updateOption.equals("Yes")) {
            System.out.println("Enter the date");
            int updateDate = librarianInt.nextInt();
            System.out.println("Enter the Month in a  format");
            int updateMonth = librarianInt.nextInt();
            System.out.println("enter the Year in a YYYY format");
            int updateYear = librarianInt.nextInt();

            LocalDate newdate = LocalDate.of(updateYear, updateMonth, updateDate);

            System.out.println("Update Date:" + newdate);
            newdifference = ChronoUnit.DAYS.between(updatedate, newdate);
            // System.out.println(newdifference);
            for (DueDate due : bookdueDate) {
                {
                    duedate = due.getdueDate();
                    // System.out.println(duedate);
                    penalty = due.getPenalty();
                }
            }
            LocalDate newduedate = LocalDate.parse(duedate);
            // System.out.println(newduedate);
            difference = ChronoUnit.DAYS.between(newdate, newduedate);
            // System.out.println(difference);
            String userid = " ";
            for (DueDate user : bookdueDate) {
                userid = user.getUserId();
            }

            System.out.println("****************************");

            System.out.println("enter userid which user currentdate will you want to edit");
            String newUserId = librarian.nextLine();

            System.out.println("***USER RENTED BOOK***");
            Iterator<DueDate> userbookItr = bookdueDate.iterator();
            while (userbookItr.hasNext()) {

                DueDate userbook = userbookItr.next();
                if (newUserId.equals(userbook.getUserId())) {

                    System.out.println(userbook.getbookTitle());

                }
            }

            System.out.println("please enter the book title you want to update");
            String booktitle = librarian.nextLine();

            if (difference == -1) {

                penalty = 1;
                // System.out.println(bookdueDate.size());
                // System.out.println(userdetail);

                Iterator<DueDate> updatepenaltyItr = bookdueDate.iterator();
                while (updatepenaltyItr.hasNext()) {
                    DueDate updatePenalty = updatepenaltyItr.next();
                    count2++;
                    // System.out.println(updatePenalty.getUserId());
                    if ((newUserId.equals(updatePenalty.getUserId()))
                            && (booktitle.equals(updatePenalty.getbookTitle()))) {
                        bookTitle = updatePenalty.getbookTitle();
                        // currentDate=updatePenalty.getcurrentDate();
                        // dueDate=updatePenalty.getdueDate();
                        userId = updatePenalty.getUserId();
                        count += count2;
                    }
                }

                count -= 1;

                bookdueDate.set(count, new DueDate(bookTitle, newdate.toString(), newduedate.toString(), penalty,
                        userId, newdifference, returnStatus));
                System.out.println("Updated Successfully");

            }

            if (difference == -2) {
                System.out.println(count + count2);
                penalty = 6;
                // System.out.println(bookdueDate.size());
                Iterator<DueDate> updatepenaltyItr = bookdueDate.iterator();
                while (updatepenaltyItr.hasNext()) {
                    DueDate updatePenalty = updatepenaltyItr.next();
                    count2++;

                    // System.out.println(updatePenalty.getUserId() + userId);
                    if ((newUserId.equals(updatePenalty.getUserId()))
                            && (booktitle.equals(updatePenalty.getbookTitle()))) {
                        bookTitle = updatePenalty.getbookTitle();
                        currentDate = updatePenalty.getcurrentDate();
                        dueDate = updatePenalty.getdueDate();
                        userId = updatePenalty.getUserId();
                        count += count2;
                    }
                }

                count -= 1;

                bookdueDate.set(count, new DueDate(bookTitle, newdate.toString(), newduedate.toString(), penalty,
                        userId, newdifference, returnStatus));
                System.out.println("Updated Successfully");
            }

            if (difference == -3) {

                penalty = 16;
                // System.out.println(bookdueDate.size());
                Iterator<DueDate> updatepenaltyItr = bookdueDate.iterator();
                while (updatepenaltyItr.hasNext()) {
                    DueDate updatePenalty = updatepenaltyItr.next();
                    count2++;

                    // System.out.println(updatePenalty.getUserId() + userId);
                    if ((newUserId.equals(updatePenalty.getUserId()))
                            && (booktitle.equals(updatePenalty.getbookTitle()))) {
                        bookTitle = updatePenalty.getbookTitle();
                        currentDate = updatePenalty.getcurrentDate();
                        dueDate = updatePenalty.getdueDate();
                        userId = updatePenalty.getUserId();
                        count += count2;
                    }
                }

                count -= 1;

                bookdueDate.set(count, new DueDate(bookTitle, newdate.toString(), newduedate.toString(), penalty,
                        userId, newdifference, returnStatus));
                System.out.println("Updated Successfully");
            }

            if (difference == -4) {

                penalty = 66;
                // System.out.println(bookdueDate.size());
                Iterator<DueDate> updatepenaltyItr = bookdueDate.iterator();
                while (updatepenaltyItr.hasNext()) {
                    DueDate updatePenalty = updatepenaltyItr.next();
                    count2++;

                    // System.out.println(updatePenalty.getUserId() + userId);
                    if ((newUserId.equals(updatePenalty.getUserId()))
                            && (booktitle.equals(updatePenalty.getbookTitle()))) {
                        bookTitle = updatePenalty.getbookTitle();
                        currentDate = updatePenalty.getcurrentDate();
                        dueDate = updatePenalty.getdueDate();
                        userId = updatePenalty.getUserId();
                        count += count2;
                    }
                }

                count -= 1;

                bookdueDate.set(count, new DueDate(bookTitle, newdate.toString(), newduedate.toString(), penalty,
                        userId, newdifference, returnStatus));
                System.out.println("Updated Successfully");
            }

            if (difference < -4) {
                penalty = 0;
                for (DueDate date : bookdueDate) {
                    if (newUserId.equals(date.getUserId())) {
                        penalty = date.getPenalty();
                        penalty += 50;
                    }
                }

                // System.out.println(bookdueDate.size());
                Iterator<DueDate> updatepenaltyItr = bookdueDate.iterator();
                while (updatepenaltyItr.hasNext()) {
                    DueDate updatePenalty = updatepenaltyItr.next();
                    count2++;

                    // System.out.println(updatePenalty.getUserId() + userId);
                    if ((newUserId.equals(updatePenalty.getUserId()))
                            && (booktitle.equals(updatePenalty.getbookTitle()))) {
                        bookTitle = updatePenalty.getbookTitle();
                        currentDate = updatePenalty.getcurrentDate();
                        dueDate = updatePenalty.getdueDate();
                        userId = updatePenalty.getUserId();
                        count += count2;
                    }
                }

                count -= 1;

                bookdueDate.set(count, new DueDate(bookTitle, newdate.toString(), newduedate.toString(), penalty,
                        userId, newdifference, returnStatus));
                System.out.println("Updated Successfully");
            }

        }
    }

    // method for show the userid and penalty who have a penalty greater than or
    // equal to 100 rupees
    public void reportDefaultList() {
        int reportPenalty = 0;
        int onePenalty = 0;
        int secondPenalty = 0;
        boolean penaltyFound = false;
        // System.out.println("USERID | TOTAL PENALTY ");
        for (int i = 0; i < bookdueDate.size() - 1; i++) {
            for (int j = i + 1; j < bookdueDate.size(); j++) {
                if (bookdueDate.get(i).getUserId().equals(bookdueDate.get(j).getUserId())) {
                    onePenalty = bookdueDate.get(i).getPenalty();
                    secondPenalty = bookdueDate.get(j).getPenalty();
                    reportPenalty = (onePenalty + secondPenalty);
                    System.out.println("USERID         |     TOTAL PENALTY  ");
                    if (reportPenalty >= 100) {
                        System.out.println(bookdueDate.get(i).getUserId() + "    |" + reportPenalty);
                        penaltyFound = true;
                    }

                }
            }
        }

        Iterator<DueDate> userreportItr = bookdueDate.iterator();
        while (userreportItr.hasNext()) {
            DueDate userReport = userreportItr.next();

            if (userReport.getPenalty() >= 100) {
                System.out.println(userReport.getUserId() + "                        |  " + userReport.getPenalty());
                penaltyFound = true;
            }
        }
        if (!penaltyFound) {
            System.out.println("Users Penalty Amount not exceed more than 100");
        }

    }

    // method for show the report of rented books
    public void reportBook() {
        System.out.println("USERID         |   BOOKTITLE    |    TOTAL PENALTY    |   DUEDATE   | RETURNSTATUS ");
        bookdueDate.sort(Comparator.comparing(DueDate::getdueDate).reversed());

        Iterator<DueDate> bookreportItr = bookdueDate.iterator();
        while (bookreportItr.hasNext()) {
            DueDate bookReport = bookreportItr.next();

            if (bookReport.getPenalty() >= 0) {

                System.out.println(bookReport.getUserId() + "                        |  " + bookReport.getbookTitle()
                        + "                                          |" + bookReport.getPenalty()
                        + "                                |" + bookReport.getdueDate() + "  |"
                        + bookReport.getreturnStatus());
            }
        }

    }

    public void updatePenalty() {
        String user;
        String bookTitle = " ";
        String currentDate = " ";
        String dueDate = " ";
        String userId = " ";
        long difference = 0;
        int count = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int newpenalty = 0;

        System.out.println("enter the user Id you want to update");
        user = librarian.nextLine();
        Iterator<DueDate> bookreportItr = bookdueDate.iterator();
        while (bookreportItr.hasNext()) {
            count2++;
            DueDate bookReport = bookreportItr.next();
            bookTitle = bookReport.getbookTitle();
            currentDate = bookReport.getcurrentDate();
            dueDate = bookReport.getdueDate();
            userId = bookReport.getUserId();
            difference = bookReport.getdifference();
            if (user.equals(bookReport.getUserId())) {
                System.out.println("PENALTY AMOUNT");
                System.out.println(bookReport.getPenalty());
                System.out.println("please enter the penalty amount you want to reduce");
                int reduce = librarianInt.nextInt();
                int penalty = bookReport.getPenalty();
                newpenalty = (penalty - reduce);
                count += count2;
                count -= 1;
                // System.out.println(bookTitle);
                bookdueDate.set(count,
                        new DueDate(bookTitle, currentDate, dueDate, newpenalty, userId, difference, returnStatus));

                System.out.println("please enter the return status of book:\n1.return\n2.not returned");
                int returnbook = librarianInt.nextInt();

                if ((returnbook == 1) && (user.equals(bookReport.getUserId()))) {
                    String updateReturnStatus = "YES";
                    bookdueDate.set(count, new DueDate(bookTitle, currentDate, dueDate, newpenalty, userId, difference,
                            updateReturnStatus));
                    for (BooksDetail bookreturn : bookDetail) {
                        count3++;
                        if (bookreturn.getBookTitle().equals(bookReport.getbookTitle())) {
                            count4 += count3;
                            count4 -= 1;
                            bookDetail.set(count4,
                                    new BooksDetail(bookreturn.getCategory(), bookreturn.getBookTitle(),
                                            bookreturn.getBookAuthor(), bookreturn.getBookRating(),
                                            bookreturn.getBookInitialQuantity(), bookreturn.getBooktotalQuantity(),
                                            bookreturn.getBookInitialQuantity()));
                            System.out.println("Book Returned");
                        }
                    }

                }
                if (returnbook == 2) {
                    System.out.println("Book Is Not Returned");
                }
            }
        }
    }
}