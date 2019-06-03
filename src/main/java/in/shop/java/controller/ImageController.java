package in.shop.java.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.shop.java.service.PictureService;

@Controller
public class ImageController {
	
	@Autowired
	PictureService pictureService;
	
	public ImageController(){};
	
	@RequestMapping(value = "/image/getImg{id}" , method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<byte[]> ListImage(@PathVariable int id) throws IOException{
		byte[] image = pictureService.getPicture(id);
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(image, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delete-picture-{id}", method=RequestMethod.GET)
	public String deletePicture(@PathVariable int id){
		pictureService.deletePictureById(id);
		return "redirect:/adminProfile";
	}
	
}
