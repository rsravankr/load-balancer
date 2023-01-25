import java.util.List;

public class RoundRobinStrategy implements InvocationStrategy {
    private int index = -1;
    @Override
    public Instance invoke(List<Provider> providers) {
        index = index % providers.size();
        return providers.get(index).get();
    }
}
