package com.product.infra;

import com.mongodb.client.MongoDatabase;
import io.nats.client.Dispatcher;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class StartupInjection {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupInjection.class);

    @Inject
    Dispatcher dispatcher;

    @Inject
    MongoDatabase mongoDatabase;

    void onStart(@Observes StartupEvent startupEvent) {
        LOGGER.info("The application is starting...");
    }

    void onStop(@Observes ShutdownEvent shutdownEvent) {
        LOGGER.info("The application is stopping...");
    }

}