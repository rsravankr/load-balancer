package strategy;

public class StrategyFactory {
    public InvocationStrategy getStrategy(StrategyType strategyType) {
        return switch (strategyType) {
            case RANDOM -> new RandomStrategy();
            case ROUND_ROBIN -> new RoundRobinStrategy();
        };
    }
}
