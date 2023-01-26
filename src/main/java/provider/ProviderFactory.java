package provider;

public class ProviderFactory implements IProviderFactory {
    public IProvider generateProvider() {
        return new Provider();
    }
}
