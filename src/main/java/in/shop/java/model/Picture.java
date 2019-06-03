package in.shop.java.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "PICTURE")
public class Picture implements Serializable {

	private static final long serialVersionUID = -5678525970145064913L;
	
	@Id
	@GeneratedValue
	@Column(name = "PICTURE_ID")
	private Integer id;

	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name="PICTURE")
	private byte[] picture;
	
	@Column(name = "IS_MAIN")
	private boolean main;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT")
	@JsonBackReference
	private Product product;
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Picture(){}
	
	public Picture(byte[] picture) {
		this.picture = picture;
	}

	public Picture(byte[] picture, Product product) {
		this.picture = picture;
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}
	
	public String toString() {
		return "" + product;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Picture))
			return false;
		Picture other = (Picture) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
