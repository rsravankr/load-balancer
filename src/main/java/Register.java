import java.util.ArrayList;
import java.util.List;

public class Register {
    private List<Provider> providers = new ArrayList<>();
    private int maxProviders;

    public Register(List<Provider> providers, int maxProviders) {
        this.providers = providers;
        this.maxProviders = maxProviders;
    }


    public void setup() {
        providers.add(new ProviderImpl());
        providers.add(new ProviderImpl());
        providers.add(new ProviderImpl());
        providers.add(new ProviderImpl());
        providers.add(new ProviderImpl());
        providers.add(new ProviderImpl());
        providers.add(new ProviderImpl());
        providers.add(new ProviderImpl());
        providers.add(new ProviderImpl());
    }

    public void registerProvoder(Provider provider) {
        if (this.providers.size() < maxProviders) {
            this.providers.add(provider);
        }
    }
}
