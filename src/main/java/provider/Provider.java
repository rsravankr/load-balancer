package provider;

import Responses.Response;

import java.util.UUID;

public class Provider implements IProvider {

    private String instanceId;
    public static int maxPerProvider = 5;

    public Provider() {
        this.instanceId = UUID.randomUUID().toString();
    }

    public Response get() {
        System.out.println(instanceId);
        return new Response(instanceId);
    }

    public boolean isAlive() {
        return true;
    }

    @Override
    public int hashCode() {
        return instanceId.hashCode();
    }
}
