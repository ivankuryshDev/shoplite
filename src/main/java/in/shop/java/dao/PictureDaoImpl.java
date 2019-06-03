package in.shop.java.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import in.shop.java.model.Picture;

@Repository("pictureDao")
public class PictureDaoImpl extends AbstractDao<Integer, Picture> implements PictureDao {
	
	static final Logger logger = LoggerFactory.getLogger(PictureDaoImpl.class);

	public Picture findById(int id) {
		Picture picture = getByKey(id);
		return picture;
	}

	public void save(Picture picture) {
		persist(picture);
	}

	@SuppressWarnings("unchecked")
	public List<Picture> findAllPictures() {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("id"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Picture> pictures = (List<Picture>)crit.list();
		return pictures;
	}

	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Picture picture = (Picture)crit.uniqueResult();
		delete(picture);
	}
	
}
