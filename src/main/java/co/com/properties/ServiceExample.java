package co.com.properties;

import org.springframework.stereotype.Service;

//@Service
@Monitoreable
public class ServiceExample implements IServiceMont {


    public void onCall(String call) {
        System.out.println(call);
    }

    @Override
    public void makeActionOnCall(String onCall) {
        this.onCall(onCall);
    }
}
