package in.shop.java.service;

import java.util.List;
import in.shop.java.model.Picture;

public interface PictureService {
	
	Picture findPictureById(int id);
	
	void savePicture(Picture picture);
	
	List<Picture> findAllPictures();
	
	public byte[] getPicture(int id);
	
	void deletePictureById(int id);
}
