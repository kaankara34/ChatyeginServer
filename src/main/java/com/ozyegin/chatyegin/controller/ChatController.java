package com.ozyegin.chatyegin.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ozyegin.chatyegin.encryption.DecryptMessage;
import com.ozyegin.chatyegin.model.EncryptedMessage;
import com.ozyegin.chatyegin.model.Message;

@Controller
public class ChatController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/message")
	@SendTo("/ozu-nero/public")
	public String receivePublicMessage(@Payload String message) {

		return message;

	}

	@MessageMapping("/private-message")
	public String receivePrivateMessage(@Payload String message) {
		System.out.println(message);
		DecryptMessage decry = new DecryptMessage();
		String decryptText = decry.decryptText(message, "my-secret-key@123");
		System.out.println("bakkk" + decryptText);
		String a = "["+decryptText+"]";
		JSONArray array = new JSONArray(a);
		String receiverName = "boş";
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			receiverName = object.getString("receiverName");
		}

		simpMessagingTemplate.convertAndSendToUser(receiverName,"/private", message); // /chatyegın/ozu-members/Kaan/private
		return message;

	}

}
