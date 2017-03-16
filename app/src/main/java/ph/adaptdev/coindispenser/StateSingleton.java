package ph.adaptdev.coindispenser;

public class StateSingleton {

    private static StateSingleton stateSingleton;

    private String apiHost;
    private String port;

    private StateSingleton() {

    }

    public static synchronized StateSingleton getInstance() {
        if (stateSingleton == null) {
            stateSingleton = new StateSingleton();
        }
        return stateSingleton;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

}
