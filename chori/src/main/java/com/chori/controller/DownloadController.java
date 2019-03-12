package com.chori.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chori.model.PiModel;
import com.chori.service.PiService;

@Controller
@RequestMapping("/download")
public class DownloadController {
	@Autowired
	PiService service;

	// private static final String FILE_PATH = "downloads/rem.jpg";
	// private static final String APPLICATION_PDF = "application/pdf";
	@RequestMapping(value = "/piattached", method = RequestMethod.GET)
	// public @ResponseBody void download(HttpServletResponse response) throws
	// IOException {
	public @ResponseBody void download(HttpServletResponse response,
			@Valid PiModel piModel, BindingResult result) throws IOException {
		System.err.println(piModel.getPiattachedfilename() + "BoBo");
		// D:\workplaces\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Chori\resources\chori\PiattachFile\
		// File file = new File("D:\\Image\\rem.jpg");
		File file = new File(
				"D:\\workplaces\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Chori\\resources\\chori\\PiattachFile\\"
						+ piModel.getPiattachedfilename());
		if (!file.exists()) {
			throw new FileNotFoundException("file with path: " + file.getName()
					+ " was not found.");
		} else {
			InputStream in = new FileInputStream(file);

			response.setContentType("application/jpg");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ file.getName());
			response.setHeader("Content-Length", String.valueOf(file.length()));
			FileCopyUtils.copy(in, response.getOutputStream());
		}
	}

	// private File getFile() throws FileNotFoundException {
	// // File file = new File(FILE_PATH);
	// File file = new File("/WEB-INF/downloads/rem.jpg");
	// if (!file.exists()){
	// throw new FileNotFoundException("file with path: " + " was not found.");
	// }
	// return file;
	// }

}