import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadBalancerImpl extends ILoadBalancer{
    private List<Provider> providers = new ArrayList<>();
    private Map<Provider, Integer> inactiveProviders = new HashMap<>();
    private int activeRequests = 0;

    @Override
    public Instance get(InvocationStrategy strategy) {

        if (activeRequests+1 > ProviderImpl.maxPerProvider * providers.size()) {
            activeRequests++;
            return strategy.invoke(providers);
        }
        return null;
    }

    @Override
    public void addProvider(Provider provider) {
        providers.add(provider);
    }

    @Override
    public void removeProvider(Provider provider) {
        providers.remove(provider);
    }

    @Override
    public void checkHeartBeat() {
        try {
            while (true) {
                removeDeadInstances();
                addActiveInstances();
                Thread.sleep(5 * 1000);
            }
        } catch (InterruptedException e) {
        }
    }

    private void removeDeadInstances() {
        for (Provider provider:providers) {
            if (!provider.isAlive()) {
                providers.remove(provider);
                inactiveProviders.put(provider, 0);
            }
        }
    }

    private void addActiveInstances() {
        for (Map.Entry<Provider, Integer> entry:inactiveProviders.entrySet()) {
            Provider key = entry.getKey();
            int count = entry.getValue();
            if (key.isAlive()) {
                count++;
                if (count >= 2) {
                    inactiveProviders.remove(key);
                    providers.add(key);
                } else {
                    inactiveProviders.put(key, count);
                }
            } else {
                inactiveProviders.put(key, 0);
            }
        }
    }
}
