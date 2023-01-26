package loadbalancer;

import Responses.Response;
import strategy.StrategyType;

public interface ILoadBalancer {
    Response get(StrategyType strategyType);

    void invoke(StrategyType strategyType);
}
