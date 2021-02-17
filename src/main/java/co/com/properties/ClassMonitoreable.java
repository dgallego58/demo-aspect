package co.com.properties;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Monitoreable
@RestController
public class ClassMonitoreable {

    private final IServiceMont iServiceMont;

/*    @Autowired
    private ServiceExample serviceExample;*/

    //JDK proxy: NO SE USA EL CGLib
    //interface -> esta lo implementa el proxy -> se desentiende con metodos y clases final , es decir interviene to-do lo que está en el contexto del interface
    // CGLIB el proxy hereda la clase

    //se recomienda usar JDK proxy, para esto la inyección y la declaración de Beans, tiene que ser a través de interfaces

    public ClassMonitoreable(IServiceMont iServiceMont) {
        this.iServiceMont = iServiceMont;
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> testAop(@RequestBody Map<String, Object> requestBody) {
        requestBody.put("elResp", "resp");//el llamado al servicio o a la cosa que se quiera monitorear
        return ResponseEntity.ok(requestBody);
    }

    @PostMapping(path = "/throw")
    public ResponseEntity<?> testAopEx(@RequestBody Map<String, Object> requestBody) throws Exception {
        requestBody.put("elResp", "resp");

        iServiceMont.makeActionOnCall((String) requestBody.get("elResp")); //cuando esta cosa tira un error
        throw new Exception("just an AOP Catch");
    }


}
