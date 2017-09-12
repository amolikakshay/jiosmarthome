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
import com.google.gson.JsonObject;
import com.jio.smarthome.dao.User;
import com.jio.smarthome.model.ResponseAction;

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
		ObjectMapper mapper = new ObjectMapper();
		ResponseAction actionsModel = mapper.readValue(bod, ResponseAction.class);
		System.out.println(""+actionsModel.getResult().getAction());
		switchAccordingAPIToActions(actionsModel.getResult().getAction(), actionsModel);
		return "";
	}
	
	
	private void switchAccordingAPIToActions(String action,ResponseAction actionsModel) {
		
		switch (action) {
		
		case "light.action":
			setLightsInput(actionsModel.getResult().getParameters().getStatus());
			break;
		case "tv.input":
			if(actionsModel.getResult().getParameters().getStatus().equalsIgnoreCase("on"))
			{
		      executePost("https://myopenhab.org/rest/items/SontBraviaTVPower",actionsModel.getResult().getParameters().getStatus());
		      executePost("https://myopenhab.org/rest/items/sonos_PLAY1_RINCON_5CAAFD7A40AC01400_control","PAUSE");
		      setLightsInput("OFF");
		 	}
			else
			{
				executePost("https://myopenhab.org/rest/items/SontBraviaTVPower",actionsModel.getResult().getParameters().getStatus());
	    	    executePost("https://myopenhab.org/rest/items/sonos_PLAY1_RINCON_5CAAFD7A40AC01400_control","PLAY");
	    	    setLightsInput("ON");
			}
			break;

		case "tv.mute":
			  executePost("https://myopenhab.org/rest/items/sony_bravia_b05bd621_7e1a_4588_9f3a_4fb314269c25_audiomute",actionsModel.getResult().getParameters().getStatus());
				break;

		case "tv.picture.toggle":
			  executePost("https://myopenhab.org/rest/items/sony_bravia_b05bd621_7e1a_4588_9f3a_4fb314269c25_picturemute",actionsModel.getResult().getParameters().getStatus());
				break;

		case "switch.action":
			  executePost("https://myopenhab.org/rest/items/zwave_device_a16dfb68_node4_switch_binary",actionsModel.getResult().getParameters().getStatus());
				break;
			
		case "volume":
			if(actionsModel.getResult().getParameters().getDeviceName()!=null)
			{
			  if(actionsModel.getResult().getParameters().getDeviceName().equalsIgnoreCase("tv"))
			  {
			    executePost("https://myopenhab.org/rest/items/SonyBraviaTV_Volume",actionsModel.getResult().getParameters().getNumber());
			  }
			  else if(actionsModel.getResult().getParameters().getDeviceName().equalsIgnoreCase("music"))
			  {
			    executePost("https://myopenhab.org/rest/items/Music_Volume",actionsModel.getResult().getParameters().getNumber());
			  }
			 break;
			}
			
		case "music_action":
			  executePost("https://myopenhab.org/rest/items/sonos_PLAY1_RINCON_5CAAFD7A40AC01400_control",actionsModel.getResult().getParameters().getMusicControlEntity().toUpperCase());
				break;
				
		case "video.play":
			  if(!actionsModel.getResult().getParameters().getMovie().isEmpty())
			  {
				  executePost("https://myopenhab.org/rest/items/JioCinema_Input","play"+" "+actionsModel.getResult().getParameters().getMovie());
			  }
			  else if(!actionsModel.getResult().getParameters().getVideo().isEmpty())
			  {
			  executePost("https://myopenhab.org/rest/items/JioCinema_Input","play"+" "+actionsModel.getResult().getParameters().getVideo());
			  }
			break;
			
    	case "video_player_control.pause":
			
	          executePost("https://myopenhab.org/rest/items/JioCinema_Input","pause");
			break;
        case "video_player_control.resume":
	
             executePost("https://myopenhab.org/rest/items/JioCinema_Input","resume");
  	         break;
	         
        case "video_player_control.skip_forward":
  	         executePost("https://myopenhab.org/rest/items/JioCinema_Input",actionsModel.getResult().getResolvedQuery());
	        break;
	        
        case "video_player_control.skip_backward":
	         executePost("https://myopenhab.org/rest/items/JioCinema_Input",actionsModel.getResult().getResolvedQuery());
  	        break;
  	
        case "video_player_control.stop":
  	        executePost("https://myopenhab.org/rest/items/JioCinema_Input","stop");
	    break;

       case "exit.jiocinema":
  	        executePost("https://myopenhab.org/rest/items/JioCinema_Input","exit");
		
       default:
			break;
    	}		
	}
		


	private void setLightsInput(String status) {

		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_1_color",status);
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_2_color",status);
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_3_color",status);
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_4_color",status);
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_5_color",status);
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_6_color",status);
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_7_color",status);
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_8_color",status);
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_9_color",status);
		executePost("https://myopenhab.org/rest/items/hue_0210_0017881c5b90_10_color",status);

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
