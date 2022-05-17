
package pack;

import java.util.*;
import java.time.*;

public class User extends Librarian {

    Scanner user = new Scanner(System.in);// create object for Scanner class used for getting Integer input
    Scanner userStr = new Scanner(System.in);// create object for Scanner class used for getting String input
    int userChoices, searchRating;// declare variable for the getting input in the userChoice method
    String searchTitle, searchAuthor, categorysName, userId;
    int penalty;
    // method for userlogin

    public void userLogin() {
        String checkuserid = " ";
        boolean found = false;
        System.out.println("************************************");
        System.out.println("enter user name:");
        String userNames = user.nextLine();
        System.out.println("enter user password");
        String UserPasswords = user.nextLine();
        System.out.println("enter user id");
        userId = userStr.nextLine();
        Iterator<userDetail> userLoginItr = userdetail.iterator();// create object for iterator
        while (userLoginItr.hasNext())// check the cursor point before the element
        {
            userDetail userLogin = userLoginItr.next();// move the cursor to the next element
            // check the user given inputs are matched to the userdetails set by the admin
            if (userLogin.getUserName().equals(userNames) && userLogin.getUserPassword().equals(UserPasswords)) {
                found = true;// change the found value to true
            }
        }
        if (!found)// if the found value true print the below statement
        {
            System.out.println("give correct usernames,userid and password!!");
        }
        for (DueDate userid : bookdueDate) {
            checkuserid = userid.getUserId();
        }
        if (((found == true) && (checkuserid == " ")) || (found == true) && (checkuserid != userId)) {
            System.out.println("***********************************");
            System.out.println("please reset the password");
            System.out.println("enter your old password");
            String oldPassWord = userStr.nextLine();
            boolean founds = false;
            ListIterator<userDetail> ChangePasswordItr = userdetail.listIterator();// create object for iterator
            while (ChangePasswordItr.hasNext())// check the cursor point before the element
            {
                userDetail changePassword = ChangePasswordItr.next();// move the cursor to the next element
                // check the user given inputs are matched to the userdetails set by the admin
                if (changePassword.getUserPassword().equals(oldPassWord)) {
                    System.out.println("enter New Password");
                    String NewPassword = userStr.nextLine();
                    ChangePasswordItr
                            .set(new userDetail(changePassword.getUserName(), NewPassword, changePassword.getUserId()));
                    founds = true;// if true, change the found value to true
                }
            }

            if (founds == true) {
                System.out.println("PassWord Successfully Changed");
                userChoice();// call the method userChoice()
            }
            if (!founds)// if the found value true print the below statement
            {
                System.out.println("give correct password!!");
            }
        }
        if (found == true) {
            System.out.println("login Successfully");
            userChoice();
        }

    }

    // getting option from user for usermenu
    public void userChoice() {
        user = new Scanner(System.in);
        System.out.println("**************************");
        System.out.println("1. Browse books by Category");
        System.out.println("2. Search book by title or author");
        int penaltycheck = 0;

        for (DueDate due : bookdueDate) {
            penaltycheck = due.getPenalty();
        }
        if (penaltycheck < 100) {
            System.out.println("3. Rent Books");
        }
        System.out.println("4. Rented Books with due date");
        System.out.println("5. logout");
        System.out.println("\n Enter your option:");
        userChoices = user.nextInt();
        while (userChoices <= 5) {
            switch (userChoices) {
                case 1:
                    browseBook();
                    userChoice();
                    break;
                case 2:
                    searchBook();
                    userChoice();
                    break;
                case 3:
                    rentBook();
                    userChoice();
                    break;
                case 4:
                    dueDate();
                    userChoice();
                case 5:
                    input();
                    return;

            }
        }

    }

    // method for browsing the books

    public void browseBook() {
        boolean find = false;
        System.out.println("******************************");
        System.out.println("**CATEGORY NAME**");
        System.out.println(category);
        System.out.println("*******************************");
        System.out.println("Browse By Category Name");
        String browseCategory = userStr.nextLine();

        System.out.println("CATEGORYNAME | BOOKTITLE | BOOKAUTHOR | RATING | TOTALQUANITY | AVAILABLE");
        Iterator<BooksDetail> browseItr = bookDetail.iterator();// create object for iterator
        while (browseItr.hasNext())// check the cursor point before the element
        {
            BooksDetail browseBook = browseItr.next();// move the cursor to the next element
            String books = browseBook.getCategory().toString();
            // check the user given inputs are matched to the categoryname set by the admin
            if (books.equals(browseCategory)) {
                System.out.println(browseBook);// if true , print the statement
                find = true;
            }
        }
        if (!find)// if the found value true print the below statement
        {
            System.out.println("category not found");
        }

    }

    // method for searching the book
    public void searchBook() {
        System.out.println("1.Search By Book Title");
        System.out.println("2.Search By Book Author");
        System.out.println("3.Search By Book Rating");
        System.out.println("enter your option");
        int searchBookOption = user.nextInt();
        while (searchBookOption <= 3) {
            switch (searchBookOption) {
                case 1:
                    System.out.println("************************");
                    System.out.print("Enter Book Title");
                    searchTitle = userStr.nextLine();

                    boolean searchFound = false;

                    System.out.println("CATEGORYNAME | BOOKTITLE | BOOKAUTHOR | RATING | TOTALQUANITY | AVAILABLE");
                    Iterator<BooksDetail> searchItr = bookDetail.iterator();// create object for iterator
                    while (searchItr.hasNext())// check the cursor point before the element
                    {
                        BooksDetail searchBooks = searchItr.next();// move the cursor to the next element
                        // check the user given inputs are matched to the bookdetails set by the admin
                        String[] title = searchBooks.getBookTitle().split(" ");

                        for (int i = 0; i < title.length; i++) {

                            if (title[i].equals(searchTitle)) {
                                System.out.println(searchBooks);// if true , print the statement
                                searchFound = true;
                            }
                        }
                        if (searchBooks.getBookTitle().equals(searchTitle)) {
                            System.out.println(searchBooks);
                            searchFound = true;
                        }
                    }

                    if (!searchFound) {
                        System.out.println("Book Not Found!!");
                    }
                    userChoice();
                    break;
                case 2:
                    System.out.println("*****************************");
                    System.out.println("Enter Book Author");
                    searchAuthor = userStr.nextLine();
                    boolean authorsearchFound = false;

                    Iterator<BooksDetail> authorsearchItr = bookDetail.iterator();// create object for iterator
                    while (authorsearchItr.hasNext())// check the cursor point before the element
                    {
                        BooksDetail authorsearchBooks = authorsearchItr.next();// move the cursor to the next element
                        // check the user given inputs are matched to the bookdetails set by the admin
                        if (authorsearchBooks.getBookAuthor().equals(searchAuthor)) {
                            System.out.println(authorsearchBooks);// if true , print the statement
                            authorsearchFound = true;
                        }
                    }
                    if (!authorsearchFound) {
                        System.out.println("Book Not Found!!");
                    }
                    userChoice();
                    break;
                case 3:
                    System.out.println("****************************");
                    System.out.println("Enter Book Rating");
                    searchRating = user.nextInt();

                    boolean ratingFound = false;
                    for (BooksDetail books : bookDetail) {
                        int rating = books.getBookRating();

                        if (rating == searchRating) {
                            System.out.println(books.getCategory());
                            System.out.println(books.getBookTitle());
                            System.out.println(books.getBookAuthor());
                            System.out.println(books.getBookRating());
                            System.out.println(books.getBookInitialQuantity());
                            System.out.println(books.getBookQuantity());
                            ratingFound = true;

                        }
                    }
                    if (!ratingFound) {
                        System.out.println("Book Not Found!!");
                    }

                    userChoice();
                    break;

            }
        }

    }

    // method for renting books
    public void rentBook() {
        System.out.println("**CATEGORY NAME**");
        System.out.println(category);

        System.out.println("enter the category name you want");
        String rentCategory = userStr.nextLine();
        boolean find = false;
        boolean bookDetailFound = false;
        boolean penaltyFound = false;

        String searchbookTitle = " ";
        int count = 0;
        int count2 = 0;
        long difference = 0;
        categorysName = category.toString();
        // System.out.println(categorysName);
        System.out.println("*****************************");
        if (categorysName.contains(rentCategory)) {
            find = true;
            System.out.println(
                    "CATEGORYNAME  | BOOKTITLE  | BOOKAUTHOR | BOOKAUTHOR | BOOKRATING | BOOKTOTALQUANTITY | BOOKAVAILABLEQUANTITY");
            for (BooksDetail books : bookDetail) {

                if (books.getCategory().toString().equals(rentCategory)) {
                    System.out.println(books);// if true , print the statement
                }

            }
            System.out.println("************************");
            System.out.println("enter the book title");
            searchbookTitle = userStr.nextLine();
            for (BooksDetail books : bookDetail) {
                // String bookTitle = books.getBookTitle();
                if (books.getBookTitle().equals(searchbookTitle)) {
                    bookDetailFound = true;
                    System.out.println("********************************");
                    System.out.println("How many quantity of  book you want to rent");
                    int userQuantity = user.nextInt();
                    int Quantity = books.getBookQuantity();
                    // int initialquantity = books.getBookInitialQuantity();
                    int rating = books.getBookRating();
                    // Object categoryName = books.getCategory();
                    // String bookAuthor = books.getBookAuthor();
                    if (userQuantity <= books.getBookInitialQuantity()) {

                        Quantity -= userQuantity;
                        // System.out.println(Quantity);
                        rating += 1;
                        // System.out.println(rating);
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
                        bookDetail.set(count,
                                new BooksDetail(books.getCategory(), books.getBookTitle(), books.getBookAuthor(),
                                        rating,
                                        books.getBookInitialQuantity(), books.getBooktotalQuantity(), Quantity));
                        System.out.println("Book Rented Successfully");
                        penaltyFound = true;
                    } else {
                        System.out.println("Quantity you want is not available");
                    }
                }
            }
        }
        if (penaltyFound = true) {
            LocalDate date = LocalDate.now();
            for (DueDate diff : bookdueDate) {
                difference = diff.getdifference();
                returnStatus = diff.getreturnStatus();
            }
            // System.out.println(difference);
            LocalDate currentdate = date.plusDays(difference);
            // System.out.println("current date:" + currentdate);
            LocalDate duedate = currentdate.plusDays(7);
            // System.out.println("due Date:" + duedate);

            Iterator<BooksDetail> showduedateItr = bookDetail.iterator();// create object for iterator
            while (showduedateItr.hasNext())// check the cursor point before the element
            {
                BooksDetail booksduedate = showduedateItr.next();
                {
                    if (searchbookTitle.equals(booksduedate.getBookTitle())) {
                        bookdueDate.add(
                                new DueDate(booksduedate.getBookTitle(), currentdate.toString(), duedate.toString(),
                                        penalty, userId, difference, returnStatus));

                    }
                }
            }
        }

        if (!bookDetailFound && find) {
            System.out.println("Book Not Found");

        }

        if (!find)// if the found value true print the below statement
        {
            System.out.println("category not found");
        }

    }

    // method for showing the rented book details to the user

    public void dueDate() {
        System.out.println("BOOKTITLE  |  CURRENTDATE   |  DUEDATE   |  PENALTY   |  RETURNSTATUS");

        Iterator<DueDate> duedateItr = bookdueDate.iterator();// create object for iterator
        while (duedateItr.hasNext())// check the cursor point before the element
        {
            DueDate booksduedate = duedateItr.next();
            {
                if (userId.equals(booksduedate.getUserId())) {
                    System.out.println(booksduedate);

                }
            }
        }

    }
}
