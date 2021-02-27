package com.splitwise.beans;

public class User {
    private String userId,userName,email,mobileNo;

    public User(String userId, String userName, String email, String mobileNo) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public static class UserBuilder{
        private String userId,userName,email,mobileNo;

        public UserBuilder(){}

        public UserBuilder setUserId(String userId){
            this.userId = userId;
            return this;
        }
        public UserBuilder setUserName(String userName){
            this.userName = userName;
            return this;
        }
        public UserBuilder setEmail(String email){
            this.email = email;
            return this;
        }
        public UserBuilder setMobileNo(String mobileNo){
            this.mobileNo = mobileNo;
            return this;
        }

        public User build(){
            return new User(this.userId,this.userName,this.email,this.mobileNo);
        }
    }

}
