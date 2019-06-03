package in.shop.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.shop.java.dao.PictureDao;
import in.shop.java.model.Picture;

@Service("pictureService")
@Transactional
public class PictureServiceImpl implements PictureService{
	
	@Autowired
	private PictureDao dao;

	public Picture findPictureById(int id) {
		return dao.findById(id);
	}

	public void savePicture(Picture picture) {
		dao.save(picture);
	}

	public List<Picture> findAllPictures() {
		return dao.findAllPictures();
	}
	
	public byte[] getPicture(int id) {
		Picture product = dao.findById(id);
		byte[] image = null;
		if (product != null) {
			Picture picture = dao.findById(id);
			image = picture.getPicture();
		}
		return image;
	}

	public void deletePictureById(int id) {
		dao.deleteById(id);
	}
}
