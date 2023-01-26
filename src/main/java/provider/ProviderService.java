package provider;

import Responses.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProviderService {
    static int MAX_PROVIDERS = 10;
    private List<Provider> providers = new ArrayList<>();
    private Map<Provider, Integer> inactiveProviders = new HashMap<>();
    public ProviderService() {}

    public Response get(int index) {
        return providers.get(index).get();
    }
    public void register(List<Provider> providers) {
        if (providers.size() > MAX_PROVIDERS) {
            this.providers = providers.subList(0, MAX_PROVIDERS);
        } else {
            this.providers = providers;
        }
        checkHeartBeat();
    }
    private void removeInactiveInstances() {
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
    public void checkHeartBeat() {
        removeInactiveInstances();
        addActiveInstances();
    }

    public int numProviders() {
        return providers.size();
    }

    public void include(Provider provider) {
        if (numProviders() < MAX_PROVIDERS) {
            providers.add(provider);
        }
    }

    public void exclude(Provider provider) {
        if (providers.contains(provider)) {
            providers.remove(provider);
        }
        if (inactiveProviders.containsKey(provider)) {
            inactiveProviders.remove(provider);
        }
    }
}
