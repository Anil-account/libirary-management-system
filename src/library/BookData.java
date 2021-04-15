package library;

public class BookData {
    String book_name, pub_name, pub_date;
    int book_id, price, stock;
    
    BookData(int book_id, String book_name, String pub_name, String pub_date, int price, int stock) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.pub_name = pub_name;
        this.pub_date = pub_date;
        this.price = price;
        this.stock = stock;
    }
}