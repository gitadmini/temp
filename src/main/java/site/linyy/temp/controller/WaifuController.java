package site.linyy.temp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WaifuController{
	
	@GetMapping("waifu")
	public String waifu() {
		return "waifu";
	}
	
}