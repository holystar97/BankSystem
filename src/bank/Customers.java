package bank;


class Customers {

 private String name;

 private String id;

 private int balance;

 private String pw;

 private int count;

 private String phone;

 private String email;

 private String account;

 public void setAccount(String pw) {

  this.pw = pw;

  String accountTemp = String.valueOf(Math.random()).substring(2, 14);

  this.account = accountTemp.substring(0, 4) + "-" + accountTemp.substring(4, 8) + "-"
    + accountTemp.substring(8, 12);

 }

 public String getAccount() {

  return account;

 }

 public void setEmail(String email) {

  this.email = email;

 }

 public String getEmail() {

  return email;

 }

 public String getPhone() {

  return phone;

 }

 public void setPhone(String phone) {

  this.phone = phone;

 }

 public Customers() {

 }

 public Customers(String name, String id, String pw, int balance) {

  this.name = name;

  this.id = id;

  this.pw = pw;

  this.balance = balance;

 }

 public void setCount(int count) {

  this.count = count;

 }

 public int getCount() {

  return this.count;

 }

 public void setName(String name) {

  this.name = name;

 }

 public String getName() {

  return this.name;

 }

 public void setId(String id) {

  this.id = id;

 }

 public String getId() {

  return this.id;

 }

 public String getPw() {

  return pw;

 }

 public void setPw(String pw) {

  this.pw = pw;

 }

 public int getBalance() {

  return balance;

 }

 public void setBalance(int balance) {

  this.balance = balance;

 }

}
