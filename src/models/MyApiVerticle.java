package models;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

    public class MyApiVerticle extends AbstractVerticle {
        private static final Logger LOGGER = LoggerFactory.getLogger(MyApiVerticle.class);
        @Override
        public void start() throws Exception {

        }
        @Override
        public void stop() throws Exception {
            LOGGER.info("Dans le stop...");
        }
    }

