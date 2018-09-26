package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.PilotModel;
import service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value="id", required=true) String id,
			@RequestParam(value="licenseNumber", required=true) String licenseNumber,
			@RequestParam(value="name", required=true) String name,
			@RequestParam(value="flyHour", required=true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
				return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping(value= {"/pilot/view/license-number", "/pilot/view/license-number/{licenseNumber}"})
	public String viewpath(@PathVariable Optional<String> licenseNumber, Model model) {
		String path = "";
		if (licenseNumber.isPresent()) {
			PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
			
			model.addAttribute("pilot", archive);
			path = "viewall-pilot";
		}
		else {
			path = "view-error";
		}
		
		return path;
	}
	
	@RequestMapping(value= {"/pilot/update/license-number", 
			"/pilot/update/license-number/{licenseNumber}",
			"/pilot/update/license-number/{licenseNumber}/fly-hour", 
			"/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}"})
	public String update(@PathVariable Optional<String> licenseNumber,
			@PathVariable Optional<Integer> flyHour, Model model) {
		String path = "";
		if (licenseNumber.isPresent() && flyHour.isPresent()) {
			pilotService.updateFlyHourPilotByLicenseNumber(licenseNumber.get(), flyHour.get());
			path = "update";
		}
		else {
			path = "update-error";
		}
		
		return path;
	}
	
	@RequestMapping(value= {"/pilot/delete/id", "/pilot/delete/id/{id}"})
	public String delete(@PathVariable Optional<String> id, Model model) {
		String path = "";
		if (id.isPresent()) {
			pilotService.deletePilotDetailById(id.get());
			path = "delete";
		}
		else {
			path = "delete-error";
		}
		
		return path;
	}
	
	
	
}
