package in.shop.java.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "SUBCATEGORY")
public class Subcategory implements Serializable {

	private static final long serialVersionUID = 755030933408135644L;
	
	@Id
	@GeneratedValue
	@Column(name = "SUBCATEGORY_ID")
	public Integer id;
	
	@Column(name = "SUBCATEGORY_NAME", unique = true)
	private String name;
	
	@ManyToOne()
	@JoinColumn(name = "PARENT_ID")
	@JsonBackReference
	private Category parent;
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Subcategory> subCategories = new ArrayList<>();

	public List<Subcategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Subcategory> subCategories) {
		this.subCategories = subCategories;
	}

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

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	public String toString() {
		return "" + name;
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
		if (!(obj instanceof Subcategory))
			return false;
		Subcategory other = (Subcategory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
