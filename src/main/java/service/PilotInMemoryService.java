package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import model.PilotModel;

@Service
public class PilotInMemoryService implements PilotService {
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		archivePilot.add(pilot);
		
	}
	
	@Override
	public List<PilotModel> getPilotList() {
		// TODO Auto-generated method stub
		return archivePilot;
	}

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		List<PilotModel> pilots = getPilotList();
		for (int i = 0; i<pilots.size(); i++) {
			if (pilots.get(i).getLicenseNumber().equals(licenseNumber)) {
				return pilots.get(i);
			}
		}
		return null;
	}

	@Override
	public PilotModel deletePilotDetailById(String id) {
		// TODO Auto-generated method stub
		List<PilotModel> pilots = getPilotList();
		for (int i = 0; i<pilots.size(); i++) {
			if (pilots.get(i).getId().equals(id)) {
				return pilots.remove(i);
			}
		}
		return null;
	}

	@Override
	public void updateFlyHourPilotByLicenseNumber(String licenseNumber, int flyHour) {
		// TODO Auto-generated method stub
		List<PilotModel> pilots = getPilotList();
		for (int i = 0; i<pilots.size(); i++) {
			if (pilots.get(i).getLicenseNumber().equals(licenseNumber)) {
				pilots.get(i).setFlyHour(flyHour);
			}
		}
	}
	
}
