package pack;

public class BooksDetail {
  Object categoryName;
  String bookTitle;
  String bookAuthor;
  int rating;
  int initialQuantity;
  int totalQuantity;
  int quantity;

  public BooksDetail(Object categoryName, String bookTitle, String bookAuthor, int rating,int initialQuantity,int totalQuantity, int quantity) {
    this.categoryName = categoryName;
    this.bookTitle = bookTitle;
    this.bookAuthor = bookAuthor;
    this.rating = rating;
    this.initialQuantity = initialQuantity;
    this.totalQuantity=totalQuantity;
    this.quantity=quantity;
  }

  public Object getCategory() {
    return categoryName;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public String getBookAuthor() {
    return bookAuthor;
  }

  public int getBookRating() {
    return rating;
  }
  public int getBookInitialQuantity() {
    return initialQuantity;
  }
  public int getBooktotalQuantity()
{
  return totalQuantity;
}
  public int getBookQuantity(){
    return quantity;
  }

  public String toString() {
    return  categoryName +"    |" + bookTitle + "   |" +bookAuthor+"    |"+rating+"   |"+totalQuantity+"   |"+quantity;
  }

}
