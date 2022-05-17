package pack;

public class userDetail {
  String userName;
  String userPassword;
  String userId;

  public userDetail(String userName, String userPassword, String userId) {
    this.userName = userName;
    this.userPassword = userPassword;
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public String getUserId() {
    return userId;
  }

  public String toString() {
    return "userName:" + userName + "\n " + "userPassword:" + userPassword + "\n" + "userId:" + userId;
  }
}
