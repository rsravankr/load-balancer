import java.util.List;

public interface InvocationStrategy {
    public Instance invoke(List<Provider> providerList);
}
