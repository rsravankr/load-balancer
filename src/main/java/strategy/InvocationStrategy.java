package strategy;

import Responses.Response;
import provider.Provider;
import provider.ProviderService;

import java.util.List;

public interface InvocationStrategy {
    public Response invoke(ProviderService providerService);
}
