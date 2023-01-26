package Task;

import loadbalancer.ILoadBalancer;
import provider.ProviderService;

import java.util.TimerTask;

public class HeartbeatChecker extends TimerTask {
    private ProviderService providerService;

    public HeartbeatChecker(ProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public void run() {
        providerService.checkHeartBeat();
    }
}
