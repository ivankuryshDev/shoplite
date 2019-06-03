package in.shop.java.model;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {
	
	MultipartFile file;
	
	List<MultipartFile> files;
    
    String description;
    
    String name;
    
    List<Subcategory> subCategories;
    
    Double price;
    
    Subcategory subCategory;
    
    Category category;
    
    List<Picture> pictures;

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Subcategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Subcategory> subCategories) {
		this.subCategories = subCategories;
	}

	public Subcategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Subcategory subCategory) {
		this.subCategory = subCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
