package socialreview.cloudant;

public class Account {

    private String _rev;
    private String _id;
    private String comment;
    private int accountId;
    private String accountType;
    private String ownerEmail;
    private String ownerName;
    private String accountOpenDate;

    public Account(){}

    public Account(boolean isStub)
    {
        this.comment = "It works";
        this.accountId = 14401;
        this.accountType = "Saving";
        this.ownerEmail = "sri@ibm.com";
        this.ownerName = "Sri Seth";
        this.accountOpenDate = "06/06/2018";
    }

    public String get_Id() {
        return _id;
    }
    public void set_Id(String id) {
        this._id = id;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getOwnerEmail() {
        return ownerEmail;
    }
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getAccountOpenDate() {
        return accountOpenDate;
    }
    public void setAccountOpenDate(String accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    public String toString() {
        return "{ id: " + _id + ",\nrev: " + _rev + ",\n:accountId " + accountId
                            + ",\n:ownerEmail " + ownerEmail + ",\n:ownerName " + ownerName + "\n}";
    }

}
