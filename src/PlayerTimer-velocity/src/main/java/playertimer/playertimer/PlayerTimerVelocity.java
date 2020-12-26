package playertimer.playertimer;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(
        id = "PlayerTimer-velocity",
        name = "PlayerTimer",
        version = "0.5",
        description = "A plugin to keep track of player's time on the server, and reward them.",
        url = "dolphln.com",
        authors = {"Dolphln"}
)
public class PlayerTimerVelocity {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
