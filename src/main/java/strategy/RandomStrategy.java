package strategy;

import Responses.Response;
import provider.Provider;
import provider.ProviderService;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements InvocationStrategy {
    private Random random = new Random();

    @Override
    public Response invoke(ProviderService providerService) {
        int index = random.nextInt(providerService.numProviders());
       return providerService.get(index);
    }
}
