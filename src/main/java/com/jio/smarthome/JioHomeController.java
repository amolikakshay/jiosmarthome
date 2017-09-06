package com.jio.smarthome;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jio.smarthome.dao.User;
import com.jio.smarthome.model.ResponseAction;
import com.jio.smarthome.model.Status;
import com.jio.smarthome.model.video.VideoActionResponse;
import com.jio.smarthome.utils.HibernateUtils;

@Controller
public class JioHomeController {

	@RequestMapping("/test")
	public ModelAndView welcomeMessage(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "lname", required = false) String lname) {
		ModelAndView view = new ModelAndView("smarthome");
		view.addObject("name", name);
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		User user = new User();
		user.setFname(name);
		user.setLname(lname);
		user.setEmail("ismd.dk@gmail.com");
		user.setMobile(9535157009L);
		session.save(user);
		session.getTransaction().commit();
		System.out.println("name" + name);
		return view;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(@RequestBody String requestBody)
			throws JsonParseException, JsonMappingException, IOException {
		ModelAndView view = new ModelAndView("smarthome");
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(requestBody, User.class);
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		return view;
	}

	@RequestMapping(value = "/test1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void test1(@RequestParam("name") String name) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Params" + name);
		return;
	}

	@RequestMapping(value = "/apiaction", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public @ResponseBody String apiAction1(@RequestBody String bod, HttpServletRequest httpRequest)
			throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Hellowrold");
		ObjectMapper mapper = new ObjectMapper();
		ResponseAction actionsModel = mapper.readValue(bod, ResponseAction.class);
		// System.out.println(actionsModel);
		System.out.println("" + actionsModel.getResult());
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_1_color",
				actionsModel.getResult().getParameters().getStatus());
		Status status = actionsModel.getStatus();
		status.setCode(200);
		status.setErrorDetails("");
		status.setErrorType("success");
		actionsModel.setStatus(status);
		return status.toString();
	}

	@RequestMapping(value = "/jiocinema", method = RequestMethod.POST)
	public @ResponseBody String jiocinema(@RequestBody String jiocinemajson) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		VideoActionResponse actionsModel = mapper.readValue(jiocinemajson, VideoActionResponse.class);
		System.out.println(jiocinemajson);
		System.out.println("" + actionsModel.getResult().getParameters().getMovie());
		executePost("https://myopenhab.org/rest/items/JioCinema_Input", actionsModel.getResult().getResolvedQuery());
		return jiocinemajson;
	}

	private int executePost(String targetUrl, String status) {

		int responseCode = 0;
		try {

			URL url = new URL(targetUrl);
			HttpsURLConnection httpCon = (HttpsURLConnection) url.openConnection();
			httpCon.setRequestProperty("User-Agent", "Mozilla/5.0");
			httpCon.setDoOutput(true);

			httpCon.setRequestMethod("POST");
			httpCon.addRequestProperty("Content-Type", "text/plain");
//			String userCredentials = "jioldcsmarthome@gmail.com:smart@home";

			// String basicAuth = "Basic " + new String(
			// java.util.Base64.getEncoder().encode(userCredentials.getBytes()));
			httpCon.addRequestProperty("Authorization", "Basic amlvbGRjc21hcnRob21lQGdtYWlsLmNvbTpzbWFydEBob21l");

			// System.out.println("Auth:"+basicAuth);

			byte[] outputInBytes = status.getBytes("UTF-8");
			OutputStream os = httpCon.getOutputStream();
			os.write(outputInBytes);
			responseCode = httpCon.getResponseCode();
			System.out.println(httpCon.getResponseCode());
			System.out.println(httpCon.getResponseMessage());
			if (responseCode == 200 || responseCode == 400) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("speech", "OK");
				jsonObject.addProperty("displayText", "OK");
				jsonObject.addProperty("source", "jiosmartserver");
				// os.write(jsonObject.toString());

			}
			os.close();
			return responseCode;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responseCode;
	}

}
