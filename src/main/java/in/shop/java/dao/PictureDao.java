package in.shop.java.dao;

import java.util.List;
import in.shop.java.model.Picture;

public interface PictureDao {
	
	Picture findById(int id);
	
	void save(Picture picture);
	
	List<Picture> findAllPictures();
	
	void deleteById(int id);
}
