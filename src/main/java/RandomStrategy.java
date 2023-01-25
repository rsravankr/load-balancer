import java.util.List;
import java.util.Random;

public class RandomStrategy implements InvocationStrategy {
    private Random random = new Random();

    @Override
    public Instance invoke(List<Provider> providers) {
        int index = random.nextInt(providers.size());
        return providers.get(index).get();
    }
}
