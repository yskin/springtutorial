package udemy.hibernatedemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "comment")
  private String commnet;

  public Review() {}

  public Review(String commnet) {
    this.commnet = commnet;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCommnet() {
    return commnet;
  }

  public void setCommnet(String commnet) {
    this.commnet = commnet;
  }

  @Override
  public String toString() {
    return "Review [id=" + id + ", commnet=" + commnet + "]";
  }

}
