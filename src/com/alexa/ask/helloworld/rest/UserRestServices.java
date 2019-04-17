package com.alexa.ask.helloworld.rest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alexa.ask.helloworld.model.Customer;
import com.alexa.ask.helloworld.model.Orders;
import com.alexa.ask.helloworld.model.Product;
import com.alexa.ask.helloworld.model.Seller;
import com.alexa.ask.helloworld.model.Shipment;
import com.alexa.ask.helloworld.util.Utility;
import com.google.gson.Gson;

/**
 * @author Kavya Kommineni
 *
 */
public class UserRestServices {

	private static final Logger log = LoggerFactory.getLogger(UserRestServices.class);

	public static HttpURLConnection connection = null;
	
	public static BufferedReader response = null;
	
	public static URL resetEndpoint = null;
	
	public static StringBuilder result;
	
	public static List<Orders> orderList = new ArrayList<Orders>();
	
	public static List<Product> productList = new ArrayList<Product>();
	
	public static List<Seller> sellerList = new ArrayList<Seller>();
	
	public static List<Shipment> shipmentList = new ArrayList<Shipment>();
	
	public static JSONArray jArray;
	
	public List<Orders> getOrders(String custId) {
		
		getResult(Utility.ORDERS_URL);
		
		orderList = new ArrayList<Orders>();

		Gson gson = new Gson(); 
		
         for(Object obj : jArray)
         {
        	 JSONObject ob = (JSONObject) obj;

        	 Customer customer = gson.fromJson(ob.getJSONObject("customer").toString(), Customer.class);
        	 
        	 Orders orders = new Orders(ob.getString("id"),ob.getDouble("cost"),ob.getString("status"),customer);
        	 
        	 if(orders.getCustomer().getId().toLowerCase().equals(custId.toLowerCase()))
        	 {
        		 orderList.add(orders); 
        	 }
         }
         
		log.info(">>>>>>>>>>>>> " + orderList);
		
		return orderList;
	} 
	
	public List<Product> getProducts(String id) {
			
			getResult(Utility.PRODUCTS_URL);
			
			productList = new ArrayList<Product>();
	
			Gson gson = new Gson(); 
			
	         for(Object obj : jArray)
	         {
	        	 JSONObject ob = (JSONObject) obj;
	
	        	 Orders order = gson.fromJson(ob.getJSONObject("order").toString(), Orders.class);
	        	 
	        	 Product product = new Product(ob.getString("id"),ob.getString("name"),ob.getString("description"),ob.getDouble("price"),ob.getInt("stock"),order);
	        	 
	        	 if(product.getOrder().getId().toLowerCase().equals(id.toLowerCase()))
	        	 {
	        		 productList.add(product);
	        	 }
	         }
	         
			log.info(">>>>>>>>>>>>> " + productList);
			
			return productList;
	} 
	
	public List<Seller> getSellers(String id) {
		
		getResult(Utility.SELLERS_URL);
		
		sellerList = new ArrayList<Seller>();

		Gson gson = new Gson(); 
		
         for(Object obj : jArray)
         {
        	 JSONObject ob = (JSONObject) obj;
        	 
        	 
        	 Product product = gson.fromJson(ob.getJSONObject("product").toString(), Product.class);
        	 
        	 
        	 Seller seller = new Seller(ob.getString("id"),ob.getString("name"),ob.getString("address"),ob.getString("number"),ob.getDouble("rating"),product);
        	 
        	 if(seller.getProduct().getId().toLowerCase().equals(id.toLowerCase()))
        	 {
        		 sellerList.add(seller); 
        	 }
         }
         
		log.info(">>>>>>>>>>>>> " + sellerList);
		
		return sellerList;
	} 
		
	public List<Shipment> getShipments(String id) {
			
			getResult(Utility.SHIPMENTS_URL);
			
			shipmentList = new ArrayList<Shipment>();
	
			Gson gson = new Gson(); 
			
	         for(Object obj : jArray)
	         {
	        	 JSONObject ob = (JSONObject) obj;
	        	 
	        	 String dateStr = ob.getString("date");
	        	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        	 Date date = null;
				try {
					date = sdf.parse(dateStr);
				} catch (ParseException e) {
					log.debug("Exception while parsing th date string");
				}
	        	 
	        	 Seller seller = gson.fromJson(ob.getJSONObject("seller").toString(), Seller.class);
	        	 
	        	 Shipment shipment = new Shipment(ob.getString("id"),date,seller);
	        	 
	        	 if(shipment.getId().toLowerCase().equals(id.toLowerCase()))
	        	 {
	        		 shipmentList.add(shipment); 
	        	 }
	        	 if(shipment.getSeller().getProduct().getOrder().getId().toLowerCase().equals(id.toLowerCase()))
	        	 {
	        		 shipmentList.add(shipment);
	        	 }
	         }
	         
			log.info(">>>>>>>>>>>>> " + shipmentList);
			
			return shipmentList;
	} 
	
	public static void getResult(String url)
	{
		try
		{
			resetEndpoint = new URL(url);
			
			connection = (HttpURLConnection) resetEndpoint.openConnection();
			
			connection.setRequestMethod("GET");
			
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.connect();
			
			 InputStream in = new BufferedInputStream(connection.getInputStream());
	         BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	         
	         result = new StringBuilder();
	         String line;
	         
	         while ((line = reader.readLine()) != null) {
	             result.append(line);
	         }
	         
	         jArray = (JSONArray) new JSONTokener(result.toString()).nextValue();
	         
		}
		catch(MalformedURLException e)
		{
			log.debug("Please enter well formed valid url");
		}
		catch(IOException e)
		{
			log.debug("IO Exception occurred at BufferedReader");
		}
		catch(Exception e)
		{
			log.debug(e.getMessage());
		}
	}
}
