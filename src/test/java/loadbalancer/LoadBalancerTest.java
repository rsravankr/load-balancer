package loadbalancer;

import org.junit.jupiter.api.Test;
import provider.Provider;
import strategy.InvocationStrategy;
import strategy.StrategyFactory;
import strategy.StrategyType;

import java.util.ArrayList;
import java.util.List;


class LoadBalancerTest {

    LoadBalancer loadBalancer = new LoadBalancer();
    StrategyFactory strategyFactory = new StrategyFactory();
    @Test
    void register() {
        List<Provider> providerList = new ArrayList<>();
        providerList.add(new Provider());
        loadBalancer.get(StrategyType.ROUND_ROBIN);
        assert true;
    }

    @Test
    void get() {

    }

    @Test
    void invoke() {
    }
}