package in.shop.java.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CART")
public class Cart implements Serializable {

	private static final long serialVersionUID = -6786972496388819940L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CART_ID")
	private Integer id;
	
	@Column(name = "IS_CHECKED")
	private boolean checked;
	
	@Column(name = "DATE")
	private Date date;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
