package loadbalancer;

import Requests.Request;
import Requests.RequestGenerator;
import Responses.Response;
import Responses.ResponseCapture;
import Task.HeartbeatChecker;
import provider.Provider;
import provider.ProviderService;
import strategy.InvocationStrategy;
import strategy.StrategyFactory;
import strategy.StrategyType;

import java.util.*;
import java.util.concurrent.Semaphore;

public class LoadBalancer implements ILoadBalancer {

    static int HEARTBEAT_FREQ = 5000;
    static int MAX_REQUEST_PER_PROVIDER = 5;
    private ProviderService providerService;
    private StrategyFactory strategyFactory;
    private Timer scheduler;
    private Semaphore threadPool;
    private RequestGenerator requestGenerator;
    private ResponseCapture responseCapture;


    public LoadBalancer() {
        providerService = new ProviderService();
        strategyFactory = new StrategyFactory();
        requestGenerator = new RequestGenerator();
        responseCapture = new ResponseCapture();
    }
    public void register(List<Provider> providers) {
        providerService.register(providers);
        if (scheduler != null) {
            scheduler.cancel();
        }
        scheduler = new Timer();
        scheduler.scheduleAtFixedRate(new HeartbeatChecker(providerService), HEARTBEAT_FREQ, HEARTBEAT_FREQ);
        threadPool = new Semaphore(providers.size() * MAX_REQUEST_PER_PROVIDER);
    }
    @Override
    public Response get(StrategyType strategyType) {
        InvocationStrategy strategy = strategyFactory.getStrategy(strategyType);
        try {
            threadPool.acquire();
            return strategy.invoke(providerService);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.release();
        }
        return null;
    }
    @Override
    public void invoke(StrategyType strategyType) {
        while (true) {
            Request request = requestGenerator.getNextRequest();
            Response response = get(strategyType);
            responseCapture.capture(request, response);
        }
    }

    public void include(Provider provider) {
        providerService.include(provider);
    }
    public void exclude(Provider provider) {
        providerService.exclude(provider);
    }
}
