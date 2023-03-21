package junit;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import junit.service.UserServiceTest;

import java.io.PrintWriter;

public class TestLauncher {
    public static void main(String[] args) {
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(DiscoverySelectors.selectClass(UserServiceTest.class))
                .build();

        launcher.execute(request, listener);


        try (PrintWriter writer = new PrintWriter(System.out)) {
            listener.getSummary().printTo(writer);
        }
    }
}
