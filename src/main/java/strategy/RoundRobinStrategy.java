package strategy;

import Responses.Response;
import provider.Provider;
import provider.ProviderService;
import strategy.InvocationStrategy;

import java.util.List;

public class RoundRobinStrategy implements InvocationStrategy {
    private int index = -1;
    @Override
    public Response invoke(ProviderService providerService) {
        if (providerService.numProviders() == 0) {
            return null;
        }
        index = (index+1) % providerService.numProviders();
        return providerService.get(index);
    }
}
