package strategy;

import org.junit.jupiter.api.Test;
import provider.Provider;
import provider.ProviderService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundRobinStrategyTest {

    RoundRobinStrategy strategy = new RoundRobinStrategy();
    ProviderService providerService = new ProviderService();
    @Test
    void invoke() {
        List<Provider> providerList = new ArrayList<>();
        providerList.add(new Provider());
        providerList.add(new Provider());
        providerList.add(new Provider());

        providerService.register(providerList);
        strategy.invoke(providerService);
        strategy.invoke(providerService);
        strategy.invoke(providerService);
        strategy.invoke(providerService);
        strategy.invoke(providerService);
        strategy.invoke(providerService);
        strategy.invoke(providerService);
    }
}