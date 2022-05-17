package pack;


public class DueDate {
    String bookTitle;
    String currentdate;
    public String dueDate;
    int penalty;
    String userId;
    long difference;
    String returnStatus;

    public  DueDate(String bookTitle,String currentdate,String dueDate,int penalty,String userId,long difference,String returnStatus)
    {
        this.bookTitle=bookTitle;
        this.currentdate=currentdate;
        this.dueDate=dueDate;
        this.penalty=penalty;
        this.userId=userId;
        this.difference=difference;
        this.returnStatus=returnStatus;
    }
 public String getbookTitle(){
    return bookTitle;
}
 public String getcurrentDate(){
     return currentdate;
 }
 public String getdueDate(){
     return dueDate;
 }
 public int getPenalty(){
     return penalty;
 }
 public String getUserId(){
     return userId;
 }
 public long getdifference(){
     return difference;
 }
 public String getreturnStatus(){
     return returnStatus;
 }
 public String toString(){
    
     return                bookTitle+" |"+currentdate+" |"+dueDate+ "  |"+penalty+"   |"+returnStatus;

 }

}
