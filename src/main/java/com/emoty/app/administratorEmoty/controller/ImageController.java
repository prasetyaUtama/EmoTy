package com.emoty.app.administratorEmoty.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.emoty.app.HomeController;
import com.emoty.app.config.DatabaseConnection;
import com.emoty.app.model.CategoryOfImage;
import com.emoty.app.model.Images;
import com.emoty.app.utils.Today;
import com.marklogic.client.document.BinaryDocumentManager;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.document.BinaryDocumentManager.MetadataExtraction;
import com.marklogic.client.io.JAXBHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;

@Controller
@RequestMapping("/adminPage/image")
public class ImageController {
	private String idImage;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView upload() {
		return new ModelAndView("uploadImageForm");

	}

	@RequestMapping(value = "/addCategories", headers = "Accept=application/json", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody
	String addCategeoriesToImage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody List<CategoryOfImage> categoryOfImage)
			throws JSONException, JAXBException {
		// Database Connection
		DatabaseConnection con = new DatabaseConnection();

		// Format Result
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();
		JAXBContext context;
		Images img = null;

		// get the xml file
		context = JAXBContext.newInstance(Images.class);
		JAXBHandle handle = new JAXBHandle(context);
		docMgr.read("/images/"+this.idImage+".xml", handle);
		img = (Images) handle.get();

		// set categories of the image

		img.setCategory(categoryOfImage);
		// create a handle on the POJOs for writing to the database
		JAXBHandle writeHandle = new JAXBHandle(context);

		// provide a handle for the POJO
		writeHandle.set(img);

		// write the POJO to the database
		docMgr.write("/images/" + this.idImage + ".xml", writeHandle);
		// System.out.println(categories.getTypeCategory().get(0));
		con.getConnection().release();
		// System.out.println(idImage);
		// json response
		JSONObject obj;
		StringWriter out = new StringWriter();
		// System.out.println(categories.size());
		obj = new JSONObject();
		obj.put("result", "true");
		obj.write(out);
		return out.toString();

	}

	@RequestMapping(value = "/save", headers = "Accept=application/json", method = RequestMethod.POST)
	public @ResponseBody
	String create(MultipartHttpServletRequest request,
			HttpServletResponse response) throws IOException,
			GeneralSecurityException, JSONException {
		// UploadedFile ufile;
		Images img;

		// json response
		JSONObject obj;
		StringWriter out = new StringWriter();
	

		// get and set image
		Iterator<String> itr = request.getFileNames();

		MultipartFile mpf = request.getFile(itr.next());
		img = new Images(mpf.getOriginalFilename(), new ArrayList(),
				Today.getDate(), mpf.getBytes().length, mpf.getContentType(),
				Base64.encode(mpf.getBytes()));

		//System.out.println(mpf.getOriginalFilename());
		//System.out.println(Base64.encode(mpf.getBytes()));

		DatabaseConnection con = new DatabaseConnection();

		// md5 encryption
		MessageDigest md = MessageDigest.getInstance("MD5");
		String nameDoc = Today.getDate().toString() + "images";
		byte[] bytesOfMessage = nameDoc.getBytes("UTF-8");
		byte[] thedigest = md.digest(bytesOfMessage);

		// System.out.println(mpf.getOriginalFilename() +" uploaded!");

		// Starting to write document to server
		XMLDocumentManager docMgr = con.getConnection().newXMLDocumentManager();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Images.class);
			// create a handle on the POJOs for writing to the database
			JAXBHandle writeHandle = new JAXBHandle(context);

			// provide a handle for the POJO
			writeHandle.set(img);

			// write the POJO to the database
			docMgr.write("/images/" + thedigest.toString() + ".xml",
					writeHandle);
			//System.out.println(thedigest.toString());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.getConnection().release();

		obj = new JSONObject();
		this.idImage = thedigest.toString();
		obj.put("result", true);
		obj.put("image", img.getImage());
		obj.write(out);
		return out.toString();

	}
}
