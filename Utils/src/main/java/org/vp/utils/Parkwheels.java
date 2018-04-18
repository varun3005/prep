package org.vp.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parkwheels {

	private static HttpClient client = new HttpClient();
	private static final String BASE_URI = "http://www.parkwheels.co.in/";
	private static final String TOKEN = "9a913cf3ea6e8a50796ec9271ffe1feae2185d03"; // Change
	private static final String UNIQUE_ID = "f37d020ebf73e408"; // Change
	private static final String CATEGORY_ID = "373"; // Gurgaon infospace
	private static final String SLOT_ID = "42"; // 28: 7am to 9am //42:2pm to 11pm to test
	private JsonParser parser = new JsonParser();

	public static void main(String[] args) {
		Parkwheels pw = new Parkwheels();
		pw.updateIds();
		String vehicleId = pw.getVehicleAndParkingDetails(); // Fetches VehicleId, parking location and ids and
																// timeSlots and ids. Parse and make configurable
		pw.bookSlot(vehicleId);
	}

	private void bookSlot(String vehicleId) {
		PostMethod post = null;
		try {
			post = new PostMethod(BASE_URI + "api/v1/userapp/booking/");
			addHeaders(post);

			JsonObject booking = new JsonObject();
			booking.addProperty("category_id", CATEGORY_ID);
			booking.addProperty("slot_id", SLOT_ID);
			booking.addProperty("vehicle_id", vehicleId);
			String listingBody = booking.toString();
			System.out.println(listingBody);
			post.setRequestBody(listingBody);
			int status = client.executeMethod(post);
			String response = post.getResponseBodyAsString();
			System.out.println(status + " - " + response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}

	}

	private String getVehicleAndParkingDetails() {
		PostMethod post = null;
		String vehileId = null;
		try {
			post = new PostMethod(BASE_URI + "api/v1/userapp/booking/bookingParameters/");
			addHeaders(post);

			JsonObject listing = new JsonObject();
			listing.addProperty("category_id", "373"); // RBS
			post.setRequestBody(listing.toString());
			int status = client.executeMethod(post);
			String response = post.getResponseBodyAsString();
			System.out.println(status + " - " + response);
			JsonObject jsonObj = parser.parse(response).getAsJsonObject();
			JsonElement vehicleDetails = jsonObj.get("vehicle_list");
			System.out.println(vehicleDetails);
			vehileId = vehicleDetails.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}
		return vehileId;
	}

	public void updateIds() {
		PostMethod post = null;
		try {
			post = new PostMethod(BASE_URI + "api/v1/users/uniqueid/update/");
			addHeaders(post);
			JsonObject auth = new JsonObject();
			auth.addProperty("unique_id", UNIQUE_ID); // RBS
			post.setRequestBody(auth.toString());
			int status = client.executeMethod(post);
			System.out.println(status + " - " + post.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}
	}

	public void addHeaders(PostMethod post) {
		post.addRequestHeader("Authorization", "Token " + TOKEN);
		post.addRequestHeader("Content-Type", "application/json");
	}

}
