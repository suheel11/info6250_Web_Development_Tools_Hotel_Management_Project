package com.mycom.myapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycom.dao.BookingDao;
import com.mycom.dao.HotelDao;
import com.mycom.pojo.Booking;
import com.mycom.pojo.Hotel;
import com.mycom.pojo.User;
import com.mycom.validator.HotelValidator;

@Controller
public class HotelController {
	
	@Autowired
	HotelDao hotelDao;
	
	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	HotelValidator hotelValidator;
	
	@RequestMapping(value = "/hotels", method = RequestMethod.GET)
	public ModelAndView displayHotels(HotelDao hotelDao, HttpServletRequest request) {
		List<Hotel> hotels = hotelDao.getAllHotels();
		return new ModelAndView("hotels", "hotels", hotels);
	}

	@RequestMapping(value = "/hoteladd", method = RequestMethod.GET)
	public String getAddHotelPage(Model model) {
		model.addAttribute("hotel",new Hotel());
		return "hoteladd";
	}

	@RequestMapping(value = "/hotelbook/*", method = RequestMethod.GET)
	public ModelAndView getBookView(HotelDao hotelDao, HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		Hotel hotel = hotelDao.getHotelById(sId);
//		request.setAttribute("hotel", hotel);
		return new ModelAndView("hotelbook", "hotel", hotel);
	}

	@RequestMapping(value = "/hoteldelete/*", method = RequestMethod.GET)
	public String deleteHotel(HotelDao hotelDao, HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		hotelDao.removeHotelById(sId);
		return "redirect:/hotels";
	}

	@RequestMapping(value = "/hotelupdate/*", method = RequestMethod.GET)
	public ModelAndView updateHotelView(HotelDao hotelDao, HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		Hotel hotel = hotelDao.getHotelById(sId);
		return new ModelAndView("hotelupdate", "hotel", hotel);
	}

	@RequestMapping(value = "/hotelupdate/*", method = RequestMethod.POST)
	public String updateHotel(HotelDao hotelDao, HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		System.out.println(id + " from hotel update post request");
		String hotelName = request.getParameter("hotelName");
		String location = request.getParameter("location");
		String image = request.getParameter("image");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		hotelDao.updateHotelById(sId, hotelName, image, location, price, description);
		return "redirect:/hotels";
	}

	@RequestMapping(value = "/hotelbook", method = RequestMethod.POST)
	public String bookHotel(BookingDao bookingDao, HttpServletRequest request) throws Exception {

		// GET CURRENT USER
		HttpSession session = request.getSession();
		User curUser = (User) session.getAttribute("curUser");

		Booking booking = new Booking();
		booking.setFirstName(request.getParameter("firstName"));
		booking.setLastName(request.getParameter("lastName"));
		booking.setHotelName(request.getParameter("hotelName"));
		booking.setPhone(request.getParameter("phone"));
		booking.setDate(request.getParameter("date"));
		booking.setCurUser(curUser.getUserEmail());

		try {
			Booking o = bookingDao.addOrder(booking);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "redirect:/hotels";
	}

	@RequestMapping(value = "/hoteldetail/*", method = RequestMethod.GET)
	public ModelAndView getHotelDetail(HotelDao hotelDao, HttpServletRequest request) {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
//System.out.println("Inside hotel detail siddddd"+sId);
		Hotel hotel = hotelDao.getHotelById(sId);
//System.out.println("Inside hotel detail////"+hotel.getPrice());
		request.setAttribute("hotel", hotel);
		return new ModelAndView("hoteldetail", "hotel", hotel);
	}

	@RequestMapping(value = "/hoteladd", method = RequestMethod.POST)
	public String addHotel(@ModelAttribute("hotel") Hotel hotel,BindingResult result, HotelDao hotelDao, HttpServletRequest request) throws IllegalStateException {
		hotelValidator.validate(hotel, result);
		if(result.hasErrors()) {
			return "hoteladd";
		}
		try {
			Hotel h = hotelDao.addHotel(hotel);
			if(h!=null) {
				return "redirect:/hotels";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@RequestMapping(value = "/viewbooking", method = RequestMethod.GET)
	public ModelAndView viewBooking(BookingDao bookingDao, HttpServletRequest request) throws Exception {
		// GET CURRENT USER
		HttpSession session = request.getSession();
		User curUser = (User) session.getAttribute("curUser");

		// GET ALL BOOKING UNDER THIS USER
		List<Booking> bookings = bookingDao.findAllOrderByUserEmail(curUser.getUserEmail());
		return new ModelAndView("viewbooking", "bookings", bookings);
	}
	
	@RequestMapping(value = "/managebooking", method = RequestMethod.GET)
	public ModelAndView manangeBookingView(BookingDao bookingDao, HttpServletRequest request) throws Exception {
		List<Booking> bookings = bookingDao.findAllOrder(); 
		return new ModelAndView("managebooking", "bookings", bookings);
	}

	@RequestMapping(value = "/hotelsearch", method = RequestMethod.POST)
	public ModelAndView searchHotel(HotelDao hotelDao, HttpServletRequest request) throws Exception {
		String searchName = request.getParameter("searchName");
		List<Hotel> hotels = hotelDao.getHotelByName(searchName);
		return new ModelAndView("hotels", "hotels", hotels);
	}

        @RequestMapping(value = "/deleteBooking/*", method = RequestMethod.GET)
	public String deleteHotel(BookingDao bookingDao, HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String sId = id.replaceAll("'", "");
		bookingDao.deleteBookingById(sId);
		return "redirect:/hotels";
                
	}
}
