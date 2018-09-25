package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.CarModel;

@Service
public class CarInMemoryService implements CarService{
	private List<CarModel> archiveCar;
	
	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
	}
	@Override
	public void addCar(CarModel car) {
		// TODO Auto-generated method stub
		archiveCar.add(car);
	}

	@Override
	public List<CarModel> getCarList() {
		// TODO Auto-generated method stub
		return archiveCar;
	}

	@Override
	public CarModel getCarDetail(String id) {
		// TODO Auto-generated method stub
		for(int i =0; i <archiveCar.size(); i++) {
			if(archiveCar.get(i).getId().equals(id)) {
				return archiveCar.get(i);
			}
		}
		return null;
	}
	

}
