


package dto;




public class User {
    private String userID;
    private String fullName;
    private String passsword;
    private String roleID;

    public User(String userID, String fullName, String passsword, String roleID) {
        this.userID = userID;
        this.fullName = fullName;
        this.passsword = passsword;
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    
    
}
