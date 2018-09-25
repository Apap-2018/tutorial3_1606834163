package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;

@Controller
public class CarController {
	@Autowired
	private CarService mobilService;
	
	@RequestMapping("/car/add")
	public String add(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "brand", required = true) String brand,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "price", required = true) Long price,
			@RequestParam(value = "amount", required = true) Integer amount) {
		CarModel car = new CarModel(id,brand,type,price,amount);
		mobilService.addCar(car);
		System.out.println("jumlah "+mobilService.getCarList().size());

		return "add";	
	}
	
//	@RequestMapping("/car/view")
//	public String view(@RequestParam("id") String id, Model model) {
//		CarModel archive = mobilService.getCarDetail(id);
//		
//		model.addAttribute("car", archive);
//		return "view-car";
//	}
	
	@RequestMapping("/car/viewall")
	public String viewall(Model model) {
		List<CarModel> archive = mobilService.getCarList();
		
		model.addAttribute("listCar", archive);
		return "viewall-car";
	}
	
	@RequestMapping(value = {"/car/view", "/car/view/{id}"})
	public String challengePath(@PathVariable Optional<String> id, Model model) {
		if(id.isPresent()) {
			System.out.println("masuk pak eko");

			List<CarModel> archive = mobilService.getCarList();

			int count = mobilService.getCarList().size();

			while(count>1) {
				count--;
				System.out.println("masuk pak ");
				
				if(archive.get(count-1).getId().equals(id)) {
					
					model.addAttribute("car", archive.get(count-1));
					return "view-car";		
				}
			}
		}

		return "noIdCar";
	}
	
	@RequestMapping(value = {"/car/update","/car/update/{id}/amount/{amount}"})
	public String updateAmount( @PathVariable("id") Optional<String> id,
			@PathVariable("amount") Integer amount,Model model) {
		if(id.isPresent()) {
			List<CarModel> listCar = mobilService.getCarList();
			for(int i = 0; i<listCar.size(); i++) {
				if(listCar.get(i).getId().equals(id)) {
					listCar.get(i).setAmount(amount);
					model.addAttribute("car", listCar.get(i));
					return "update-amount";		
				}
			}
		}
		return "noIdCar";
	}
	
	@RequestMapping(value = {"/car/delete","/car/delete/{id}"})
	public String delete( @PathVariable("id") Optional<String> id,Model model) {
		if(id.isPresent()) {
			List<CarModel> listCar = mobilService.getCarList();
			for(int i = 0; i<listCar.size(); i++) {
				if(listCar.get(i).getId().equals(id)) {
					model.addAttribute("car", listCar.get(i));
					listCar.get(i).setId(null);
					return "deleted";		
				}
			}
		}
		return "noIdCar";
	}


}
