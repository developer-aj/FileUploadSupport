package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	 @RequestMapping(value = "greeting", method = RequestMethod.GET, headers = "Accept=application/json")
	 public @ResponseBody Greeting greeting(){
		return new Greeting(counter.incrementAndGet(), String.format(template, "World"));
	}
}
