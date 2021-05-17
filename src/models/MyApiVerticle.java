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
            LOGGER.info("Dans le start...");
            final Router router = Router.router(vertx);
            final ApiResource apiResource = new ApiResource();
            final Router apiSubRouter = apiResource.getSubRouter(vertx);
            router.mountSubRouter("/api/v1/communique", apiSubRouter);
            vertx.createHttpServer()
                    .requestHandler(router::accept)
                    .listen(9990);
        }
        @Override
        public void stop() throws Exception {
            LOGGER.info("Dans le stop...");
        }
    }

