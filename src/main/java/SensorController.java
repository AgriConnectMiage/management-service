import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SensorController {
    // get all sensors
    @RequestMapping("/sensors")
    public String getSensors() {
        return "All sensors";
    }
}
