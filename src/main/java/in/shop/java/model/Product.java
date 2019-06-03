package in.shop.java.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PRODUCT")
public class Product  implements Serializable{

	private static final long serialVersionUID = -5049898729696230339L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PRODUCT_ID")
	private Integer id;

	@Column(name="PRODUCT_NAME")
	private String name;
	
	@Column(name="PRODUCT_DESCRIPTION")
	private String description;
	
	@Column(name="PRODUCT_PRICE")
	private Double price = 0.0;
	
	/*,orphanRemoval = true*/
	@OneToMany(mappedBy="product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	public List<Picture> pictures = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "SUBCATEGORY")
	private Subcategory subCategory;

	public List<Picture> getPictures() {
		return pictures;
	}

	public Subcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Subcategory subCategory) {
		this.subCategory = subCategory;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public Product() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String toString() {
		return "" + subCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

